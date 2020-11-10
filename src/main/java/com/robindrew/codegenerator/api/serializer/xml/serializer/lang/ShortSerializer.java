package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class ShortSerializer extends ValueSerializer<Short> {

	public ShortSerializer(String name) {
		super(name);
	}

	@Override
	public Short readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		return Short.parseShort(value);
	}
}
