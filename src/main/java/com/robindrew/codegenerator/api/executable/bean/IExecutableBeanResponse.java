package com.robindrew.codegenerator.api.executable.bean;

import java.io.Serializable;

import com.robindrew.codegenerator.api.error.IErrorCodeSetContainer;

/**
 * An Executable Bean Response.
 * @param <R> the return type.
 */
public interface IExecutableBeanResponse<R> extends IErrorCodeSetContainer, Serializable {

	int getBeanId();

	R getReturnValue();

	long getDuration();

	boolean hasErrors();

	boolean hasReturnValue();

}
