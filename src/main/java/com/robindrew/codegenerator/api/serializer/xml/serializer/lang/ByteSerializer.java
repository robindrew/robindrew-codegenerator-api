package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class ByteSerializer extends ValueSerializer<Byte> {

	public ByteSerializer(String name) {
		super(name);
	}

	@Override
	public Byte readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		return Byte.parseByte(value);
	}

}
