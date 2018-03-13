package com.robindrew.codegenerator.api.executable.executor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBeanResponse;
import com.robindrew.codegenerator.api.executable.executor.IBeanExecutorService;

public abstract class BatchBeanExecutorService implements IBeanExecutorService {

	@SuppressWarnings("unchecked")
	@Override
	public <R, B extends IExecutableBean<R>> IExecutableBeanResponse<R> executeBean(B bean) {

		// A batch executor always executes a batch of beans, so we wrap a single execution
		List<IExecutableBean<?>> beanList = new ArrayList<>();
		beanList.add(bean);
		Map<IExecutableBean<?>, IExecutableBeanResponse<?>> beanToResponseMap = executeBeanList(beanList);
		IExecutableBeanResponse<?> response = beanToResponseMap.get(bean);
		if (response == null) {
			throw new IllegalStateException("Invalid response to bean: " + bean + ", response=" + response);
		}
		return (IExecutableBeanResponse<R>) response;
	}

}
