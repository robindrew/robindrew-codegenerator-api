package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class IntArraySerializer extends ValueSerializer<int[]> {

	public IntArraySerializer(String name) {
		super(name);
	}

	@Override
	public int[] readValue(String value) {
		ValueArray values = new ValueArray(value);
		int[] array = new int[values.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = Integer.parseInt(values.next());
		}
		return array;
	}

	@Override
	public String writeValue(int[] array) {
		boolean comma = false;
		StringBuilder value = new StringBuilder();
		for (int element : array) {
			if (comma) {
				value.append(',');
			}
			comma = true;
			value.append(element);
		}
		return value.toString();
	}
}
