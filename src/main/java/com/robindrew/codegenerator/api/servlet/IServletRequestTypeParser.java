package com.robindrew.codegenerator.api.servlet;

import javax.servlet.ServletRequest;

public interface IServletRequestTypeParser {

	byte parseByte(ServletRequest request, String name);

	short parseShort(ServletRequest request, String name);

	int parseInt(ServletRequest request, String name);

	long parseLong(ServletRequest request, String name);

	float parseFloat(ServletRequest request, String name);

	double parseDouble(ServletRequest request, String name);

	char parseChar(ServletRequest request, String name);

	boolean parseBoolean(ServletRequest request, String name);

	String parseString(ServletRequest request, String name, boolean optional);

	<E extends Enum<E>> E parseEnum(ServletRequest request, String name, Class<E> enumClass, boolean optional);

	String[] parseStringArray(ServletRequest request, String name, boolean optional);

	byte[] parseByteArray(ServletRequest request, String name, boolean optional);

}