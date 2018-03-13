package com.robindrew.codegenerator.api.sql.parser;

import java.sql.ResultSet;

/**
 * The {@link ResultSetTypes} utility. Provides simplified and uniform parsing of basic data types from a
 * {@link ResultSet}
 */
public class ResultSetTypes {

	private ResultSetTypes() {
	}

	public static String parseString(ResultSet set, int index, String name, boolean optional) {
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

	public static byte[] parseByteArray(ResultSet set, int index, String name, boolean optional) {
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

	public static byte parseByte(ResultSet set, int index, String name) {
		try {
			return set.getByte(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("byte value: '" + name + "' failed to parse", e);
		}
	}

	public static short parseShort(ResultSet set, int index, String name) {
		try {
			return set.getShort(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("short value: '" + name + "' failed to parse", e);
		}
	}

	public static int parseInt(ResultSet set, int index, String name) {
		try {
			return set.getInt(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("int value: '" + name + "' failed to parse", e);
		}
	}

	public static long parseLong(ResultSet set, int index, String name) {
		try {
			return set.getLong(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("long value: '" + name + "' failed to parse", e);
		}
	}

	public static float parseFloat(ResultSet set, int index, String name) {
		try {
			return set.getFloat(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("float value: '" + name + "' failed to parse", e);
		}
	}

	public static double parseDouble(ResultSet set, int index, String name) {
		try {
			return set.getDouble(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("double value: '" + name + "' failed to parse", e);
		}
	}

	public static boolean parseBoolean(ResultSet set, int index, String name) {
		try {
			return set.getBoolean(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("byte value: '" + name + "' failed to parse", e);
		}
	}

	public static char parseChar(ResultSet set, int index, String name) {
		try {
			return set.getString(index).charAt(0);
		} catch (Exception e) {
			throw new IllegalArgumentException("char value: '" + name + "' failed to parse", e);
		}
	}

	public static <E extends Enum<E>> E parseEnum(ResultSet set, int index, String name, Class<E> enumClass, boolean optional) {
		Number value = getValue(set, index, name, Number.class, optional);
		if (value == null) {
			return null;
		}
		int ordinal = value.intValue();
		return enumClass.getEnumConstants()[ordinal];
	}

	@SuppressWarnings("unchecked")
	private static <V> V getValue(ResultSet set, int index, String name, Class<V> valueClass, boolean optional) {
		final Object object;
		try {
			object = set.getObject(index);
		} catch (Exception e) {
			throw new IllegalArgumentException("value: '" + name + "' failed to parse", e);
		}
		if (object == null && !optional) {
			throw new IllegalArgumentException("value: '" + name + "' is null and not optional");
		}
		return (V) object;
	}

}
