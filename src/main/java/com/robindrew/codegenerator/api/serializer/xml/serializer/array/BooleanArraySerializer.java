package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class BooleanArraySerializer extends ValueSerializer<boolean[]> {

	public BooleanArraySerializer(String name) {
		super(name);
	}

	@Override
	public boolean[] readValue(String value) {
		ValueArray values = new ValueArray(value);
		boolean[] array = new boolean[values.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = Boolean.parseBoolean(values.next());
		}
		return array;
	}

	@Override
	public String writeValue(boolean[] array) {
		boolean comma = false;
		StringBuilder value = new StringBuilder();
		for (boolean element : array) {
			if (comma) {
				value.append(',');
			}
			comma = true;
			value.append(element);
		}
		return value.toString();
	}
}
