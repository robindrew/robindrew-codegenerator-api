package com.robindrew.codegenerator.api.sql.parser;

import java.sql.ResultSet;

public interface IResultSetTypeParser {

	String parseString(ResultSet set, int index, String name, boolean optional);

	byte[] parseByteArray(ResultSet set, int index, String name, boolean optional);

	byte parseByte(ResultSet set, int index, String name);

	short parseShort(ResultSet set, int index, String name);

	int parseInt(ResultSet set, int index, String name);

	long parseLong(ResultSet set, int index, String name);

	float parseFloat(ResultSet set, int index, String name);

	double parseDouble(ResultSet set, int index, String name);

	boolean parseBoolean(ResultSet set, int index, String name);

	char parseChar(ResultSet set, int index, String name);

	<E extends Enum<E>> E parseEnum(ResultSet set, int index, String name, Class<E> enumClass, boolean optional);

}