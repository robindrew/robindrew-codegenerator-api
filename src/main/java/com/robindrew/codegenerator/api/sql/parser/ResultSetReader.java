package com.robindrew.codegenerator.api.sql.parser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetReader<R> {

	private final IResultSetAdapter<R> adapter;

	public ResultSetReader(IResultSetAdapter<R> adapter) {
		if (adapter == null) {
			throw new NullPointerException("adapter");
		}
		this.adapter = adapter;
	}

	public List<R> readList(ResultSet results) throws SQLException {
		List<R> list = new ArrayList<R>();
		while (results.next()) {
			R row = adapter.adapt(results);
			list.add(row);
		}
		return list;
	}

	public R read(ResultSet results) throws SQLException {
		if (results.next()) {
			return adapter.adapt(results);
		}
		return null;
	}
}
