package com.robindrew.codegenerator.api.error;

import java.io.Serializable;
import java.util.Set;

public interface IErrorCodeSet extends Iterable<IErrorCode>, Serializable {

	int size();

	boolean isEmpty();

	IErrorCode getFirstError();

	Set<IErrorCode> getCodeSet();

	boolean contains(IErrorCode code);

	IErrorCodeAttributes add(IErrorCode code);

	IErrorCodeAttributes get(IErrorCode code);

}
