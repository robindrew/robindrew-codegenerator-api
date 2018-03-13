package com.robindrew.codegenerator.api.sql.builder;

import java.util.concurrent.locks.ReadWriteLock;

import com.robindrew.codegenerator.api.datastore.IDataStore;
import com.robindrew.codegenerator.api.sql.executor.ISqlExecutor;

public interface ISqlTable<R> extends IDataStore<R> {

	ReadWriteLock getReentrantLock();

	String getTable();

	String getDatabase();

	ISqlExecutor getExecutor();

	void setTable(String table);

	void setDatabase(String database);

	void setExecutor(ISqlExecutor executor);

}
