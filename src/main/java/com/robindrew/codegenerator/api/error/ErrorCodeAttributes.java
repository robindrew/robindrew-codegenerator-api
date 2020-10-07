package com.robindrew.codegenerator.api.error;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class ErrorCodeAttributes implements IErrorCodeAttributes {

	private static final long serialVersionUID = -9143667296668746645L;

	private Map<String, String> attributeMap = null;

	@Override
	public int size() {
		return attributeMap == null ? 0 : attributeMap.size();
	}

	@Override
	public IErrorCodeAttributes set(String key, String value) {
		if (key.isEmpty()) {
			throw new IllegalArgumentException("key is empty");
		}
		if (value.isEmpty()) {
			throw new IllegalArgumentException("value is empty");
		}
		if (attributeMap == null) {
			attributeMap = new HashMap<String, String>();
		}
		attributeMap.put(key, value);
		return this;
	}

	@Override
	public Set<String> keySet() {
		if (attributeMap == null) {
			return Collections.emptySet();
		}
		return attributeMap.keySet();
	}

	@Override
	public String getValue(String key) {
		if (attributeMap == null) {
			return null;
		}
		return attributeMap.get(key);
	}

	@Override
	public String toString() {
		return attributeMap == null ? "{}" : attributeMap.toString();
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof ErrorCodeAttributes) {
			ErrorCodeAttributes compare = (ErrorCodeAttributes) object;
			EqualsBuilder equals = new EqualsBuilder();
			equals.append(this.attributeMap, compare.attributeMap);
			return equals.isEquals();
		}
		return false;
	}

	@Override
	public IErrorCodeAttributes setAll(IErrorCodeAttributes attributes) {
		for (String key : attributes.keySet()) {
			set(key, attributes.getValue(key));
		}
		return this;
	}

}
