package com.robindrew.codegenerator.api.sql.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.util.concurrent.AtomicDouble;

public abstract class SqlBuilder implements ISqlBuilder {

	private final StringBuilder sql = new StringBuilder();

	protected StringBuilder getSql() {
		return sql;
	}

	protected String escape(String value) {
		if (value.indexOf('\'') == -1) {
			return value;
		}

		// The common way to escape a single quote is with another ...
		// Override if this if this is not correct
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '\'') {
				builder.append('\'');
			}
			builder.append(c);
		}
		return builder.toString();
	}

	protected ISqlBuilder sqlValue(Object value, boolean escape) {
		if (value == null) {
			return sql("NULL");
		}

		// Convert to string
		String text = String.valueOf(value);
		if (text.length() == 0) {
			return sql("''");
		}

		// Escaping could be costly, and sometimes we already know it is not necessary ...
		if (escape) {
			return sql('\'').sql(escape(text)).sql('\'');
		} else {
			return sql('\'').sql(text).sql('\'');
		}
	}

	@Override
	public String toString() {
		return sql.toString();
	}

	@Override
	public int length() {
		return sql.length();
	}

	@Override
	public char charAt(int index) {
		return sql.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return sql.subSequence(start, end);
	}

	@Override
	public ISqlBuilder sql(Object text) {
		getSql().append(text);
		return this;
	}

	@Override
	public ISqlBuilder sql(String text) {
		getSql().append(text);
		return this;
	}

	@Override
	public ISqlBuilder sql(char character) {
		getSql().append(character);
		return this;
	}

	@Override
	public ISqlBuilder database(String name) {
		return sql(name);
	}

	@Override
	public ISqlBuilder table(String name) {
		return sql('\"').sql(name).sql('\"');
	}

	@Override
	public ISqlBuilder column(String name) {
		return sql('\"').sql(name).sql('\"');
	}

	@Override
	public ISqlBuilder value(Object value) {
		if (value == null) {
			return sql("NULL");
		}
		if (value instanceof Number) {
			return value((Number) value);
		}
		return sqlValue(value, true);
	}

	@Override
	public ISqlBuilder value(Number value) {
		if (value instanceof Byte) {
			return value(value.byteValue());
		}
		if (value instanceof Short) {
			return value(value.shortValue());
		}
		if (value instanceof Integer) {
			return value(value.intValue());
		}
		if (value instanceof Long) {
			return value(value.longValue());
		}
		if (value instanceof Float) {
			return value(value.floatValue());
		}
		if (value instanceof Double) {
			return value(value.doubleValue());
		}
		if (value instanceof AtomicInteger) {
			return value(value.intValue());
		}
		if (value instanceof AtomicLong) {
			return value(value.longValue());
		}
		if (value instanceof AtomicDouble) {
			return value(value.doubleValue());
		}
		return sqlValue(value, true);
	}

	@Override
	public ISqlBuilder value(String value) {
		return sqlValue(value, true);
	}

	@Override
	public ISqlBuilder value(byte value) {
		return sql(String.valueOf(value));
	}

	@Override
	public ISqlBuilder value(short value) {
		return sql(String.valueOf(value));
	}

	@Override
	public ISqlBuilder value(int value) {
		return sql(String.valueOf(value));
	}

	@Override
	public ISqlBuilder value(long value) {
		return sql(String.valueOf(value));
	}

	@Override
	public ISqlBuilder value(float value) {
		return sql(String.valueOf(value));
	}

	@Override
	public ISqlBuilder value(double value) {
		return sql(String.valueOf(value));
	}

	@Override
	public ISqlBuilder value(boolean value) {
		return value(String.valueOf(value));
	}

	@Override
	public ISqlBuilder value(char value) {
		return value(String.valueOf(value));
	}

	@Override
	public ISqlBuilder values(Object... values) {
		boolean comma = false;
		for (Object value : values) {
			if (comma) {
				comma();
			}
			comma = true;
			value(value);
		}
		return this;
	}

	@Override
	public ISqlBuilder values(Collection<? extends Object> values) {
		boolean comma = false;
		for (Object value : values) {
			if (comma) {
				comma();
			}
			comma = true;
			value(value);
		}
		return this;
	}

	@Override
	public ISqlBuilder where() {
		return sql(" WHERE ");
	}

	@Override
	public ISqlBuilder or() {
		return sql(" OR ");
	}

	@Override
	public ISqlBuilder and() {
		return sql(" AND ");
	}

	@Override
	public ISqlBuilder notNull() {
		return sql(" NOT NULL");
	}

	@Override
	public ISqlBuilder autoIncrement() {
		return sql(" AUTO_INCREMENT");
	}

	@Override
	public ISqlBuilder comma() {
		return sql(',');
	}

	@Override
	public ISqlBuilder select() {
		return sql("SELECT ");
	}

	@Override
	public ISqlBuilder update() {
		return sql("UPDATE ");
	}

	@Override
	public ISqlBuilder from(String table) {
		return sql(" FROM ").table(table);
	}

	@Override
	public ISqlBuilder columns(String... columns) {
		boolean comma = false;
		for (String column : columns) {
			if (comma) {
				comma();
			}
			comma = true;
			column(column);
		}
		return this;
	}

	@Override
	public ISqlBuilder columns(Collection<? extends String> columns) {
		boolean comma = false;
		for (String column : columns) {
			if (comma) {
				comma();
			}
			comma = true;
			column(column);
		}
		return this;
	}

	@Override
	public ISqlBuilder selectAllFrom(String table) {
		return select().sql('*').from(table);
	}

	@Override
	public ISqlBuilder selectCountAllFrom(String table) {
		return select().sql("COUNT(*)").from(table);
	}

	@Override
	public ISqlBuilder selectColumnsFrom(String table, String... columns) {
		return this;
	}

	@Override
	public ISqlBuilder selectColumnsFrom(String table, Collection<? extends String> columns) {
		return this;
	}

	@Override
	public ISqlBuilder updateSet(String table) {
		return update().table(table).sql(" SET ");
	}

	@Override
	public ISqlBuilder dropTable(String table) {
		return sql("DROP TABLE ").table(table);
	}

	@Override
	public ISqlBuilder deleteFrom(String table) {
		return sql("DELETE FROM ").table(table);
	}

	@Override
	public ISqlBuilder createTable(String table) {
		return sql("CREATE TABLE ").table(table);
	}

	@Override
	public ISqlBuilder insertInto(String table) {
		return sql("INSERT INTO ").table(table);
	}

	@Override
	public ISqlBuilder insertIntoValues(String table, String... columns) {
		return insertInto(table).sql('(').columns(columns).sql(") VALUES ");
	}

	@Override
	public ISqlBuilder insertIntoValues(String table, Collection<? extends String> columns) {
		return insertInto(table).sql('(').columns(columns).sql(") VALUES ");
	}

	@Override
	public ISqlBuilder insertIntoValues(String table, Collection<? extends String> columns, Collection<? extends Object> values) {
		return insertIntoValues(table, columns).sql('(').values(values).sql(')');
	}

	@Override
	public ISqlBuilder truncateTable(String table) {
		return sql("TRUNCATE TABLE ").table(table);
	}

	@Override
	public ISqlBuilder insertIntoValues(String table, Map<String, Object> map) {
		List<String> columns = new ArrayList<String>(map.size());
		List<Object> values = new ArrayList<Object>(map.size());
		for (Entry<String, Object> entry : map.entrySet()) {
			columns.add(entry.getKey());
			values.add(entry.getValue());
		}
		return insertIntoValues(table, columns, values);
	}

}
