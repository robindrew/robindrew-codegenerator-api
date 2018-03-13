package com.robindrew.codegenerator.api.executable.executor.adapter;

import com.robindrew.codegenerator.api.error.ErrorCodeException;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBeanResponse;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutor;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorService;

/**
 * An {@link IBeanExecutor} that delegates all its requests to an {@link IBeanExecutorService}.
 */
public class ExecutorToServiceAdapter<R, B extends IExecutableBean<R>> implements IBeanExecutor<R, B> {

	private final IBeanExecutorService service;

	public ExecutorToServiceAdapter(IBeanExecutorService service) {
		if (service == null) {
			throw new NullPointerException("service");
		}
		this.service = service;
	}

	@Override
	public R executeBean(B bean) {
		IExecutableBeanResponse<R> response = service.executeBean(bean);
		if (response.hasErrors()) {
			throw new ErrorCodeException(response.getErrors());
		}
		return response.getReturnValue();
	}
}