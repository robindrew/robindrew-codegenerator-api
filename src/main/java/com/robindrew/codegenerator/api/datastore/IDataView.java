package com.robindrew.codegenerator.api.datastore;

import java.util.List;
import java.util.concurrent.locks.Lock;

public interface IDataView<R> {

	Lock getReadLock();

	boolean exists();

	int size();

	boolean isEmpty();

	boolean contains(R row);

	List<R> getAll();
}
