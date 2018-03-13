package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class BooleanSerializer extends ValueSerializer<Boolean> {

	public BooleanSerializer(String name) {
		super(name);
	}

	@Override
	public Boolean readValue(String value) {
		return Boolean.parseBoolean(value);
	}

}
