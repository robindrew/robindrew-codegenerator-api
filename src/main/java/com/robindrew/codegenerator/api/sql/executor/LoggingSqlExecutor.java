package com.robindrew.codegenerator.api.sql.executor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robindrew.codegenerator.api.sql.parser.IResultSetAdapter;

public class LoggingSqlExecutor implements ISqlExecutor {

	private static final Logger log = LoggerFactory.getLogger(LoggingSqlExecutor.class);

	private final ISqlExecutor delegate;

	public LoggingSqlExecutor(ISqlExecutor delegate) {
		if (delegate == null) {
			throw new NullPointerException("delegate");
		}
		this.delegate = delegate;
	}

	@Override
	public int getCount(CharSequence sql) {
		log.info("getCount({})", sql);
		return delegate.getCount(sql);
	}

	@Override
	public int getCount(CharSequence sql, ISqlValues values) {
		log.info("getCount({}, {})", sql, values);
		return delegate.getCount(sql, values);
	}

	@Override
	public void execute(CharSequence sql) {
		log.info("execute({})", sql);
		delegate.execute(sql);
	}

	@Override
	public void execute(CharSequence sql, ISqlValues values) {
		log.info("execute({}, {})", sql, values);
		delegate.execute(sql, values);
	}

	@Override
	public int executeAutoIncrement(CharSequence sql, ISqlValues values) {
		log.info("executeAutoIncrement({}, {})", sql, values);
		return delegate.executeAutoIncrement(sql, values);
	}

	@Override
	public <R> R get(CharSequence sql, IResultSetAdapter<R> parser) {
		log.info("get({})", sql);
		return delegate.get(sql, parser);
	}

	@Override
	public <R> R get(CharSequence sql, IResultSetAdapter<R> parser, ISqlValues values) {
		log.info("get({}, {})", sql, values);
		return delegate.get(sql, parser, values);
	}

	@Override
	public <R> List<R> getList(CharSequence sql, IResultSetAdapter<R> parser) {
		log.info("getList({})", sql);
		return delegate.getList(sql, parser);
	}

	@Override
	public <R> List<R> getList(CharSequence sql, IResultSetAdapter<R> parser, ISqlValues values) {
		log.info("getList({}, {})", sql, values);
		return delegate.getList(sql, parser, values);
	}

}
