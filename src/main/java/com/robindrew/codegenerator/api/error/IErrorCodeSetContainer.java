package com.robindrew.codegenerator.api.error;

import java.io.Serializable;

public interface IErrorCodeSetContainer extends Serializable {

	IErrorCodeSet getErrors();

}
