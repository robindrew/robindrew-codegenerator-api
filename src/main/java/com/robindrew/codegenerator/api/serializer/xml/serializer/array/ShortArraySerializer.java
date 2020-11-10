package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class ShortArraySerializer extends ValueSerializer<short[]> {

	public ShortArraySerializer(String name) {
		super(name);
	}

	@Override
	public short[] readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		ValueArray values = new ValueArray(value);
		short[] array = new short[values.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = Short.parseShort(values.next());
		}
		return array;
	}

	@Override
	public String writeValue(short[] array) {
		boolean comma = false;
		StringBuilder value = new StringBuilder();
		for (short element : array) {
			if (comma) {
				value.append(',');
			}
			comma = true;
			value.append(element);
		}
		return value.toString();
	}
}
