package com.robindrew.codegenerator.api.error;

import java.io.Serializable;
import java.util.Set;

public interface IErrorCodeAttributes extends Serializable {

	int size();

	IErrorCodeAttributes set(String key, String value);

	IErrorCodeAttributes setAll(IErrorCodeAttributes attributes);

	Set<String> keySet();

	String getValue(String key);

}
