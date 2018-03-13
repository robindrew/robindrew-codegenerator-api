package com.robindrew.codegenerator.api.sql.executor;

import java.sql.PreparedStatement;

public interface ISqlValues {

	ISqlValues add(byte value);

	ISqlValues add(short value);

	ISqlValues add(int value);

	ISqlValues add(long value);

	ISqlValues add(float value);

	ISqlValues add(double value);

	ISqlValues add(boolean value);

	ISqlValues add(char value);

	ISqlValues add(String value);

	ISqlValues add(byte[] value);

	<E extends Enum<E>> ISqlValues add(E value);

	void populate(PreparedStatement statement);

}
