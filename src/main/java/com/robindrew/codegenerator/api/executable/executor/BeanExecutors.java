package com.robindrew.codegenerator.api.executable.executor;

import com.robindrew.codegenerator.api.error.IErrorCode;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.api.executable.executor.adapter.ExecutorToServiceAdapter;
import com.robindrew.codegenerator.api.executable.executor.adapter.LocatorToServiceAdapter;
import com.robindrew.codegenerator.api.executable.executor.adapter.ServiceToLocatorAdapter;

public class BeanExecutors {

	/**
	 * Adapts an {@link IBeanExecutorService} to an {@link IBeanExecutor}.
	 */
	public static final <R, B extends IExecutableBean<R>> IBeanExecutor<R, B> toBeanExecutor(IBeanExecutorService service) {
		return new ExecutorToServiceAdapter<>(service);
	}

	/**
	 * Adapts an {@link IBeanExecutorLocator} to an {@link IBeanExecutorService}.
	 */
	public static final IBeanExecutorService toBeanExecutorService(IBeanExecutorLocator locator, IErrorCode internalServerErrorCode) {
		return new LocatorToServiceAdapter(locator, internalServerErrorCode);
	}

	/**
	 * Adapts an {@link IBeanExecutorService} to an {@link IBeanExecutorLocator}.
	 */
	public static IBeanExecutorLocator toBeanLocator(IBeanExecutorService service) {
		return new ServiceToLocatorAdapter(service);
	}

	private BeanExecutors() {
	}
}
