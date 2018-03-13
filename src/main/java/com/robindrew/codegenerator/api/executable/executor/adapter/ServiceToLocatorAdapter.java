package com.robindrew.codegenerator.api.executable.executor.adapter;

import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.api.executable.executor.BeanExecutors;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutor;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorLocator;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorService;

/**
 * An {@link IBeanExecutorLocator} that uses an {@link IBeanExecutorService} to execute beans sequentially.
 */
public class ServiceToLocatorAdapter implements IBeanExecutorLocator {

	private final IBeanExecutorService service;

	public ServiceToLocatorAdapter(IBeanExecutorService service) {
		if (service == null) {
			throw new NullPointerException("service");
		}
		this.service = service;
	}

	@Override
	public <R, B extends IExecutableBean<R>> IBeanExecutor<R, B> locateExecutor(IExecutableBean<R> bean) {
		return BeanExecutors.toBeanExecutor(service);
	}

}
