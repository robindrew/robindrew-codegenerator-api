package com.robindrew.codegenerator.api.executable.executor.adapter;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.robindrew.codegenerator.api.error.ErrorCodeException;
import com.robindrew.codegenerator.api.error.ErrorCodeSet;
import com.robindrew.codegenerator.api.error.IErrorCode;
import com.robindrew.codegenerator.api.executable.bean.ExecutableBeanResponse;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBeanResponse;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorLocator;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorService;

/**
 * An {@link IBeanExecutorService} that uses an {@link IBeanExecutorLocator} to execute beans sequentially.
 */
public class LocatorToServiceAdapter implements IBeanExecutorService {

	private static final Logger log = LoggerFactory.getLogger(LocatorToServiceAdapter.class);

	private final AtomicLong executionCount = new AtomicLong(0);
	private long executionLimit = SECONDS.toMillis(1);

	private final IBeanExecutorLocator locator;
	private final IErrorCode internalServerErrorCode;

	public LocatorToServiceAdapter(IBeanExecutorLocator locator, IErrorCode internalServerErrorCode) {
		if (locator == null) {
			throw new NullPointerException("locator");
		}
		if (internalServerErrorCode == null) {
			throw new NullPointerException("internalServerErrorCode");
		}
		this.locator = locator;
		this.internalServerErrorCode = internalServerErrorCode;
	}

	public long getExecutionLimit() {
		return executionLimit;
	}

	public void setExecutionLimit(long limit) {
		if (limit < 1) {
			throw new IllegalArgumentException("limit=" + limit);
		}
		this.executionLimit = SECONDS.toMillis(limit);
	}

	@Override
	public <R, B extends IExecutableBean<R>> IExecutableBeanResponse<R> executeBean(B bean) {
		long number = executionCount.incrementAndGet();
		Stopwatch timer = Stopwatch.createStarted();
		int beanId = bean.getSerializationId();
		try {
			log.info("[Bean #" + number + "] Request: " + bean);

			R returnValue = locator.locateExecutor(bean).executeBean(bean);
			long duration = stop(timer);

			IExecutableBeanResponse<R> response = new ExecutableBeanResponse<>(beanId, duration, returnValue);

			// Log response
			log(number, bean, response, timer);

			return response;

		} catch (ErrorCodeException ece) {
			long duration = stop(timer);

			IExecutableBeanResponse<R> response = new ExecutableBeanResponse<>(beanId, duration, ece.getErrors());
			if (log.isDebugEnabled()) {
				log.debug("[Bean #" + number + "] Response: " + response + " in " + timer);
			} else {
				log.info("[Bean #" + number + "] Request: " + bean + ", Errors: " + ece.getErrors() + " in " + timer);
			}
			return response;

		} catch (Throwable t) {
			long duration = stop(timer);

			// Internal Server Error!
			String errorId = UUID.randomUUID().toString();

			ErrorCodeSet errors = new ErrorCodeSet();
			errors.add(getInternalServerErrorCode()).set("errorId", errorId);

			IExecutableBeanResponse<R> response = new ExecutableBeanResponse<>(beanId, duration, errors);
			log.error("[Bean #" + number + "] Request: " + bean + " Failed in " + timer + " (errorId=" + errorId + ")", t);
			return response;
		}
	}

	private long stop(Stopwatch timer) {
		if (timer.isRunning()) {
			timer.stop();
		}
		return timer.elapsed(MILLISECONDS);
	}

	private <R, B extends IExecutableBean<R>> void log(long number, B bean, IExecutableBeanResponse<R> response, Stopwatch timer) {
		try {

			// Exceeded execution limit?
			if (timer.elapsed(MILLISECONDS) > getExecutionLimit()) {
				log.info("[Bean #" + number + "] Request: " + bean + " in " + timer);
				return;
			}

			// Debug enabled?
			if (log.isDebugEnabled()) {
				log.debug("[Bean #" + number + "] Request: " + bean + " in " + timer);
				return;
			}
		} catch (Exception e) {
			log.warn("[Bean #" + number + "] Request: " + bean + " Error debugging response!", e);
		}
	}

	@Override
	public Map<IExecutableBean<?>, IExecutableBeanResponse<?>> executeBeanList(List<IExecutableBean<?>> beanList) {
		Map<IExecutableBean<?>, IExecutableBeanResponse<?>> beanToResponseMap = new LinkedHashMap<>();
		for (IExecutableBean<?> bean : beanList) {
			IExecutableBeanResponse<?> response = executeBean(bean);
			beanToResponseMap.put(bean, response);
		}
		return beanToResponseMap;
	}

	public IErrorCode getInternalServerErrorCode() {
		return internalServerErrorCode;
	}

}
