package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class DoubleSerializer extends ValueSerializer<Double> {

	public DoubleSerializer(String name) {
		super(name);
	}

	@Override
	public Double readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		return Double.parseDouble(value);
	}
}
