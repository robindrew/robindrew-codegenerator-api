package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class IntegerSerializer extends ValueSerializer<Integer> {

	public IntegerSerializer(String name) {
		super(name);
	}

	@Override
	public Integer readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		return Integer.parseInt(value);
	}

}
