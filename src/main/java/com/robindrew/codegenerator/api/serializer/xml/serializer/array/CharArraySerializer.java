package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class CharArraySerializer extends ValueSerializer<char[]> {

	public CharArraySerializer(String name) {
		super(name);
	}

	@Override
	public char[] readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		ValueArray values = new ValueArray(value);
		char[] array = new char[values.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = values.next().charAt(0);
		}
		return array;
	}

	@Override
	public String writeValue(char[] array) {
		boolean comma = false;
		StringBuilder value = new StringBuilder();
		for (char element : array) {
			if (comma) {
				value.append(',');
			}
			comma = true;
			value.append(element);
		}
		return value.toString();
	}
}
