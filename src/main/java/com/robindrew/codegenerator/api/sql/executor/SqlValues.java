package com.robindrew.codegenerator.api.sql.executor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.robindrew.common.util.Java;

public class SqlValues implements ISqlValues {

	private final List<Object> list = new ArrayList<Object>();

	@Override
	public ISqlValues add(byte value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(short value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(int value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(long value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(float value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(double value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(boolean value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(char value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(String value) {
		list.add(value);
		return this;
	}

	@Override
	public ISqlValues add(byte[] value) {
		list.add(value);
		return this;
	}

	@Override
	public <E extends Enum<E>> ISqlValues add(E value) {
		return add((short) value.ordinal());
	}

	@Override
	public void populate(PreparedStatement statement) {
		try {
			int index = 1;
			for (Object value : list) {
				statement.setObject(index++, value);
			}
		} catch (SQLException e) {
			throw Java.propagate(e);
		}
	}

	@Override
	public String toString() {
		return list.toString();
	}

}
