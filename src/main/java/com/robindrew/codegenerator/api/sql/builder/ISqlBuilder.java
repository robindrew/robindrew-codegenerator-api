package com.robindrew.codegenerator.api.sql.builder;

import java.util.Collection;
import java.util.Map;

public interface ISqlBuilder extends CharSequence {

	ISqlBuilder sql(Object text);

	ISqlBuilder sql(String text);

	ISqlBuilder sql(char character);

	ISqlBuilder database(String name);

	ISqlBuilder table(String name);

	ISqlBuilder column(String name);

	ISqlBuilder value(Object value);

	ISqlBuilder value(Number value);

	ISqlBuilder value(String value);

	ISqlBuilder value(byte[] value);

	ISqlBuilder value(byte value);

	ISqlBuilder value(short value);

	ISqlBuilder value(int value);

	ISqlBuilder value(long value);

	ISqlBuilder value(float value);

	ISqlBuilder value(double value);

	ISqlBuilder value(boolean value);

	ISqlBuilder value(char value);

	ISqlBuilder values(Object... values);

	ISqlBuilder values(Collection<? extends Object> values);

	ISqlBuilder where();

	ISqlBuilder or();

	ISqlBuilder and();

	ISqlBuilder comma();

	ISqlBuilder select();

	ISqlBuilder update();

	ISqlBuilder notNull();

	ISqlBuilder autoIncrement();

	ISqlBuilder from(String table);

	ISqlBuilder columns(String... columns);

	ISqlBuilder columns(Collection<? extends String> columns);

	ISqlBuilder selectAllFrom(String table);

	ISqlBuilder selectCountAllFrom(String table);

	ISqlBuilder selectColumnsFrom(String table, String... columns);

	ISqlBuilder selectColumnsFrom(String table, Collection<? extends String> columns);

	ISqlBuilder updateSet(String table);

	ISqlBuilder dropTable(String table);

	ISqlBuilder truncateTable(String table);

	ISqlBuilder deleteFrom(String table);

	ISqlBuilder createTable(String table);

	ISqlBuilder insertInto(String table);

	ISqlBuilder insertIntoValues(String table, String... columns);

	ISqlBuilder insertIntoValues(String table, Collection<? extends String> columns);

	ISqlBuilder insertIntoValues(String table, Collection<? extends String> columns, Collection<? extends Object> values);

	ISqlBuilder insertIntoValues(String table, Map<String, Object> values);
}
