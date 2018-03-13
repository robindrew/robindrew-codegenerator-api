package com.robindrew.codegenerator.api.sql.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robindrew.codegenerator.api.sql.parser.IResultSetAdapter;
import com.robindrew.codegenerator.api.sql.parser.ResultSetReader;
import com.robindrew.common.util.Java;

public class SqlExecutor implements ISqlExecutor {

	private static final Logger log = LoggerFactory.getLogger(SqlExecutor.class);

	private final DataSource connector;

	public SqlExecutor(DataSource connector) {
		if (connector == null) {
			throw new NullPointerException("connector");
		}
		this.connector = connector;
	}

	private Connection getConection() throws SQLException {
		return connector.getConnection();
	}

	private PreparedStatement getStatement(Connection connection, CharSequence sql, ISqlValues values) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
		values.populate(statement);
		return statement;
	}

	private int getCount(ResultSet results) throws SQLException {
		if (results.next()) {
			return results.getInt(1);
		}
		return 0;
	}

	@Override
	public int getCount(CharSequence sql) {
		try (Connection connection = getConection()) {
			try (Statement statement = connection.createStatement()) {
				ResultSet results = statement.executeQuery(sql.toString());
				return getCount(results);
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

	@Override
	public int getCount(CharSequence sql, ISqlValues values) {
		try (Connection connection = getConection()) {
			try (PreparedStatement statement = getStatement(connection, sql, values)) {
				ResultSet results = statement.executeQuery();
				return getCount(results);
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

	@Override
	public void execute(CharSequence sql) {
		try (Connection connection = getConection()) {
			try (Statement statement = connection.createStatement()) {
				statement.executeUpdate(sql.toString());
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

	@Override
	public void execute(CharSequence sql, ISqlValues values) {
		try (Connection connection = getConection()) {
			try (PreparedStatement statement = getStatement(connection, sql, values)) {
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

	@Override
	public int executeAutoIncrement(CharSequence sql, ISqlValues values) {
		try (Connection connection = getConection()) {
			try (PreparedStatement statement = getStatement(connection, sql, values)) {
				statement.executeUpdate();
				ResultSet keys = statement.getGeneratedKeys();
				keys.next();
				return keys.getInt(1);
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

	@Override
	public <R> R get(CharSequence sql, IResultSetAdapter<R> parser) {
		try (Connection connection = getConection()) {
			try (Statement statement = connection.createStatement()) {
				ResultSet results = statement.executeQuery(sql.toString());
				return new ResultSetReader<R>(parser).read(results);
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

	@Override
	public <R> R get(CharSequence sql, IResultSetAdapter<R> parser, ISqlValues values) {
		try (Connection connection = getConection()) {
			try (PreparedStatement statement = getStatement(connection, sql, values)) {
				ResultSet results = statement.executeQuery(sql.toString());
				return new ResultSetReader<R>(parser).read(results);
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

	@Override
	public <R> List<R> getList(CharSequence sql, IResultSetAdapter<R> parser) {
		try (Connection connection = getConection()) {
			try (Statement statement = connection.createStatement()) {
				ResultSet results = statement.executeQuery(sql.toString());
				return new ResultSetReader<R>(parser).readList(results);
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

	@Override
	public <R> List<R> getList(CharSequence sql, IResultSetAdapter<R> parser, ISqlValues values) {
		try (Connection connection = getConection()) {
			try (PreparedStatement statement = getStatement(connection, sql, values)) {
				ResultSet results = statement.executeQuery(sql.toString());
				return new ResultSetReader<R>(parser).readList(results);
			}
		} catch (SQLException e) {
			log.warn("Failed: " + sql);
			throw Java.propagate(e);
		}
	}

}
