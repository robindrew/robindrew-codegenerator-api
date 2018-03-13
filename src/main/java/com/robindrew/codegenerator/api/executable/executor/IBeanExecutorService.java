package com.robindrew.codegenerator.api.executable.executor;

import java.util.List;
import java.util.Map;

import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBeanResponse;

/**
 * A bean executor that can execute any bean and that does not throw errors, but instead wraps them in an
 * {@link IExecutableBeanResponse}.
 */
public interface IBeanExecutorService {

	/**
	 * Execute a bean.
	 * @param bean the bean to execute.
	 * @return the result of the execution, whether successful or an error was thrown.
	 */
	<R, B extends IExecutableBean<R>> IExecutableBeanResponse<R> executeBean(B bean);

	/**
	 * Execute a list of beans in sequence.
	 * @param beanList the list of beans to execute.
	 * @return the results of the each execution, whether successful or an error was thrown.
	 */
	Map<IExecutableBean<?>, IExecutableBeanResponse<?>> executeBeanList(List<IExecutableBean<?>> beanList);

}
