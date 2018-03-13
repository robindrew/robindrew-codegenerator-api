package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class StringSerializer extends ValueSerializer<String> {

	public StringSerializer(String name) {
		super(name);
	}

	@Override
	public String readValue(String value) {
		return value;
	}
}
