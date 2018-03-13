package com.robindrew.codegenerator.api.error;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class ErrorCodeSet implements IErrorCodeSet {

	private static final long serialVersionUID = 1334123827582978433L;

	private Map<IErrorCode, IErrorCodeAttributes> codeToAttributeMap = new LinkedHashMap<IErrorCode, IErrorCodeAttributes>();

	@Override
	public IErrorCodeAttributes add(IErrorCode code) {
		if (code == null) {
			throw new NullPointerException();
		}
		IErrorCodeAttributes attributes = codeToAttributeMap.get(code);
		if (attributes == null) {
			attributes = new ErrorCodeAttributes();
			codeToAttributeMap.put(code, attributes);
		}
		return attributes;
	}

	public ErrorCodeSet() {
	}

	public ErrorCodeSet(IErrorCode code) {
		add(code);
	}

	public ErrorCodeSet(IErrorCode... codes) {
		for (IErrorCode code : codes) {
			add(code);
		}
	}

	@Override
	public int size() {
		return codeToAttributeMap.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public Set<IErrorCode> getCodeSet() {
		return new LinkedHashSet<IErrorCode>(codeToAttributeMap.keySet());
	}

	@Override
	public IErrorCodeAttributes get(IErrorCode code) {
		IErrorCodeAttributes attributes = codeToAttributeMap.get(code);
		if (attributes == null) {
			throw new IllegalArgumentException("code not found: " + code);
		}
		return attributes;
	}

	@Override
	public IErrorCode getFirstError() {
		return codeToAttributeMap.keySet().iterator().next();
	}

	@Override
	public Iterator<IErrorCode> iterator() {
		return getCodeSet().iterator();
	}

	@Override
	public String toString() {
		return codeToAttributeMap.toString();
	}

	@Override
	public boolean contains(IErrorCode code) {
		return codeToAttributeMap.containsKey(code);
	}

	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof ErrorCodeSet) {
			ErrorCodeSet compare = (ErrorCodeSet) object;
			EqualsBuilder equals = new EqualsBuilder();
			equals.append(this.codeToAttributeMap, compare.codeToAttributeMap);
			return equals.isEquals();
		}
		return false;
	}

}
