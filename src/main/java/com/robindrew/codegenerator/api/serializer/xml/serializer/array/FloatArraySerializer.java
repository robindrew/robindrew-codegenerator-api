package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class FloatArraySerializer extends ValueSerializer<float[]> {

	public FloatArraySerializer(String name) {
		super(name);
	}

	@Override
	public float[] readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		ValueArray values = new ValueArray(value);
		float[] array = new float[values.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = Float.parseFloat(values.next());
		}
		return array;
	}

	@Override
	public String writeValue(float[] array) {
		boolean comma = false;
		StringBuilder value = new StringBuilder();
		for (float element : array) {
			if (comma) {
				value.append(',');
			}
			comma = true;
			value.append(element);
		}
		return value.toString();
	}
}
