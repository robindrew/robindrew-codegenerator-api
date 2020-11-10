package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class LongSerializer extends ValueSerializer<Long> {

	public LongSerializer(String name) {
		super(name);
	}

	@Override
	public Long readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		return Long.parseLong(value);
	}

}
