package com.robindrew.codegenerator.api.servlet;

import java.util.Arrays;

import javax.servlet.ServletRequest;

/**
 * The {@link ServletRequestTypeParser} utility. Provides simplified and uniform parsing of basic data types from a
 * {@link ServletRequest}
 */
public class ServletRequestTypeParser implements IServletRequestTypeParser {

	private String getValue(ServletRequest request, String name, boolean optional) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		String value = request.getParameter(name);
		if (value == null && !optional) {
			throw new IllegalArgumentException("parameter: '" + name + "' has no value and is not optional");
		}
		return value;
	}

	private String[] getValues(ServletRequest request, String name, boolean optional) {
		if (name == null) {
			throw new NullPointerException("name");
		}
		String[] values = request.getParameterValues(name);
		if (values == null && !optional) {
			throw new IllegalArgumentException("parameter: '" + name + "' has no value and is not optional");
		}
		return values;
	}

	/* (non-Javadoc)
	 * @see com.javabi.codetype.servlet.IServletRequestTypeParser#parseByte(javax.servlet.ServletRequest, java.lang.String)
	 */
	@Override
	public byte parseByte(ServletRequest request, String name) {
		String value = getValue(request, name, false);
		try {
			return Byte.parseByte(value);
		} catch (Exception e) {
			throw new IllegalArgumentException("byte parameter: '" + name + "' has invald value: '" + value + "'");
		}
	}

	@Override
	public short parseShort(ServletRequest request, String name) {
		String value = getValue(request, name, false);
		try {
			return Short.parseShort(value);
		} catch (Exception e) {
			throw new IllegalArgumentException("short parameter: '" + name + "' has invald value: '" + value + "'");
		}
	}

	@Override
	public int parseInt(ServletRequest request, String name) {
		String value = getValue(request, name, false);
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			throw new IllegalArgumentException("int parameter: '" + name + "' has invald value: '" + value + "'");
		}
	}

	@Override
	public long parseLong(ServletRequest request, String name) {
		String value = getValue(request, name, false);
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			throw new IllegalArgumentException("long parameter: '" + name + "' has invald value: '" + value + "'");
		}
	}

	@Override
	public float parseFloat(ServletRequest request, String name) {
		String value = getValue(request, name, false);
		try {
			return Float.parseFloat(value);
		} catch (Exception e) {
			throw new IllegalArgumentException("float parameter: '" + name + "' has invald value: '" + value + "'");
		}
	}

	@Override
	public double parseDouble(ServletRequest request, String name) {
		String value = getValue(request, name, false);
		try {
			return Double.parseDouble(value);
		} catch (Exception e) {
			throw new IllegalArgumentException("double parameter: '" + name + "' has invald value: '" + value + "'");
		}
	}

	@Override
	public char parseChar(ServletRequest request, String name) {
		String value = getValue(request, name, false);
		if (value.length() != 1) {
			throw new IllegalArgumentException("char parameter: '" + name + "' has invald value: '" + value + "'");
		}
		return value.charAt(0);
	}

	@Override
	public boolean parseBoolean(ServletRequest request, String name) {
		String value = getValue(request, name, false);
		if (value == null) {
			return false;
		}
		value = value.toLowerCase();
		return value.equals("true") || value.equals("on") || value.equals("yes");
	}

	@Override
	public String parseString(ServletRequest request, String name, boolean optional) {
		return getValue(request, name, true);
	}

	@Override
	public <E extends Enum<E>> E parseEnum(ServletRequest request, String name, Class<E> enumClass, boolean optional) {
		String value = getValue(request, name, optional);
		if (value == null) {
			return null;
		}
		try {
			return Enum.valueOf(enumClass, value);
		} catch (Exception e) {
			throw new IllegalArgumentException("enum parameter: '" + name + "' has invald value: '" + value + "'");
		}
	}

	@Override
	public String[] parseStringArray(ServletRequest request, String name, boolean optional) {
		String[] values = getValues(request, name, optional);
		if (values == null) {
			return null;
		}
		return values;
	}

	@Override
	public byte[] parseByteArray(ServletRequest request, String name, boolean optional) {
		String[] values = getValues(request, name, optional);
		if (values == null) {
			return null;
		}
		try {
			byte[] array = new byte[values.length];
			for (int i = 0; i < values.length; i++) {
				array[i] = Byte.parseByte(values[i]);
			}
			return array;
		} catch (Exception e) {
			throw new IllegalArgumentException("byte[] parameter: '" + name + "' has invald value: '" + Arrays.toString(values) + "'");
		}
	}
}
