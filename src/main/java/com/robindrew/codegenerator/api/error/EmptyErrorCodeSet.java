package com.robindrew.codegenerator.api.error;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class EmptyErrorCodeSet implements IErrorCodeSet {

	private static final long serialVersionUID = 8423206819862852672L;

	@Override
	public Iterator<IErrorCode> iterator() {
		return getCodeSet().iterator();
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public IErrorCode getFirstError() {
		throw new IllegalStateException("set is empty");
	}

	@Override
	public Set<IErrorCode> getCodeSet() {
		return Collections.emptySet();
	}

	@Override
	public boolean contains(IErrorCode code) {
		return false;
	}

	@Override
	public IErrorCodeAttributes add(IErrorCode code) {
		throw new IllegalStateException("set is immutable");
	}

	@Override
	public IErrorCodeAttributes get(IErrorCode code) {
		throw new IllegalStateException("set is empty");
	}

}
