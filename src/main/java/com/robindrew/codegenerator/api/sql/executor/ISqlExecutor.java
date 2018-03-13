package com.robindrew.codegenerator.api.sql.executor;

import java.util.List;

import com.robindrew.codegenerator.api.sql.parser.IResultSetAdapter;

public interface ISqlExecutor {

	int getCount(CharSequence sql);

	int getCount(CharSequence sql, ISqlValues values);

	void execute(CharSequence sql);

	void execute(CharSequence sql, ISqlValues values);

	int executeAutoIncrement(CharSequence sql, ISqlValues values);

	<R> R get(CharSequence sql, IResultSetAdapter<R> parser);

	<R> R get(CharSequence sql, IResultSetAdapter<R> parser, ISqlValues values);

	<R> List<R> getList(CharSequence sql, IResultSetAdapter<R> parser);

	<R> List<R> getList(CharSequence sql, IResultSetAdapter<R> parser, ISqlValues values);

}
