package com.robindrew.codegenerator.api.executable.executor;

import com.robindrew.codegenerator.api.executable.bean.IExecutableBean;

/**
 * A locator that can lookup the {@link IBeanExecutor} for a given bean.
 */
public interface IBeanExecutorLocator {

	<R, B extends IExecutableBean<R>> IBeanExecutor<R, B> locateExecutor(IExecutableBean<R> bean);

}
