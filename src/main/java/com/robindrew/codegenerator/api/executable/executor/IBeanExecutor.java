package com.robindrew.codegenerator.api.executable.executor;

import com.robindrew.codegenerator.api.error.ErrorCodeException;
import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;

/**
 * A simple bean executor that throws an {@link ErrorCodeException} if any expected errors occur.
 */
public interface IBeanExecutor<R, B extends IExecutableBean<R>> {

	/**
	 * Execute the bean.
	 * @param bean the bean to execute.
	 * @return the result of executing the bean.
	 */
	R executeBean(B bean) throws ErrorCodeException;

}
