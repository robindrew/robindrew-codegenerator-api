package com.robindrew.codegenerator.api.sql.parser;

import java.sql.ResultSet;

/**
 * The {@link ResultSetTypeParser} utility. Provides simplified and uniform parsing of basic data types from a
 * {@link ResultSet}
 */
public class ResultSetTypeParser implements IResultSetTypeParser {

	@Override
	public String parseString(ResultSet set, int index, String name, boolean optional) {
		final String value;
		try {
			value = set.getString(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("String value: '" + name + "' failed to parse", e);
		}
		if (value == null && !optional) {
			throw new IllegalArgumentException("String value: '" + name + "' has no value and is not optional");
		}
		return value;
	}

	@Override
	public byte[] parseByteArray(ResultSet set, int index, String name, boolean optional) {
		final byte[] value;
		try {
			value = set.getBytes(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("byte[] value: '" + name + "' failed to parse", e);
		}
		if (value == null && !optional) {
			throw new IllegalArgumentException("byte[] value: '" + name + "' has no value and is not optional");
		}
		return value;
	}

	@Override
	public byte parseByte(ResultSet set, int index, String name) {
		try {
			return set.getByte(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("byte value: '" + name + "' failed to parse", e);
		}
	}

	@Override
	public short parseShort(ResultSet set, int index, String name) {
		try {
			return set.getShort(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("short value: '" + name + "' failed to parse", e);
		}
	}

	@Override
	public int parseInt(ResultSet set, int index, String name) {
		try {
			return set.getInt(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("int value: '" + name + "' failed to parse", e);
		}
	}

	@Override
	public long parseLong(ResultSet set, int index, String name) {
		try {
			return set.getLong(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("long value: '" + name + "' failed to parse", e);
		}
	}

	@Override
	public float parseFloat(ResultSet set, int index, String name) {
		try {
			return set.getFloat(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("float value: '" + name + "' failed to parse", e);
		}
	}

	@Override
	public double parseDouble(ResultSet set, int index, String name) {
		try {
			return set.getDouble(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("double value: '" + name + "' failed to parse", e);
		}
	}

	@Override
	public boolean parseBoolean(ResultSet set, int index, String name) {
		try {
			return set.getBoolean(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("byte value: '" + name + "' failed to parse", e);
		}
	}

	@Override
	public char parseChar(ResultSet set, int index, String name) {
		try {
			return set.getString(index).charAt(0);
		} catch (Exception e) {
			throw new IllegalArgumentException("char value: '" + name + "' failed to parse", e);
		}
	}

	@Override
	public <E extends Enum<E>> E parseEnum(ResultSet set, int index, String name, Class<E> enumClass, boolean optional) {
		Short value = getValue(set, index, name, Short.class, optional);
		if (value == null) {
			return null;
		}
		int ordinal = value.intValue();
		return enumClass.getEnumConstants()[ordinal];
	}

	@SuppressWarnings("unchecked")
	private <V> V getValue(ResultSet set, int index, String name, Class<V> valueClass, boolean optional) {
		final Object object;
		try {
			object = set.getObject(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("value: '" + name + "' failed to parse", e);
		}
		if (object == null && !optional) {
			throw new IllegalArgumentException("value: '" + name + "' is null and not optional");
		}
		// Fix for JDBC issue where SMALLINT maps to Integer instead of Short
		if (valueClass == Short.class && object instanceof Integer) {
			return (V) new Short(((Integer) object).shortValue());
		}
		return (V) object;
	}

}
