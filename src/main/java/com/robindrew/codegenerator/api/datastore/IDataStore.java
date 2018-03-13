package com.robindrew.codegenerator.api.datastore;

import java.util.Collection;
import java.util.concurrent.locks.Lock;

public interface IDataStore<R> extends IDataView<R> {

	Lock getWriteLock();

	void create();

	void destroy();

	void clear();

	void remove(R row);

	void add(R row);

	void set(R row);

	void addAll(Collection<? extends R> elements);

	void setAll(Collection<? extends R> elements);

	void removeAll(Collection<? extends R> elements);

}
