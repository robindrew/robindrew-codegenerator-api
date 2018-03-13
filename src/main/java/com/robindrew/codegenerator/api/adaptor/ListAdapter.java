package com.robindrew.codegenerator.api.adaptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListAdapter<F, T> {

	private final IAdapter<F, ? extends T> adapter;

	public ListAdapter(IAdapter<F, ? extends T> adapter) {
		if (adapter == null) {
			throw new NullPointerException("adapter");
		}
		this.adapter = adapter;
	}

	public List<T> adapt(Collection<? extends F> fromList) {
		List<T> toList = new ArrayList<T>();
		for (F from : fromList) {
			T to = adapter.adapt(from);
			toList.add(to);
		}
		return toList;
	}

}
