package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class FloatSerializer extends ValueSerializer<Float> {

	public FloatSerializer(String name) {
		super(name);
	}

	@Override
	public Float readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		return Float.parseFloat(value);
	}

}
