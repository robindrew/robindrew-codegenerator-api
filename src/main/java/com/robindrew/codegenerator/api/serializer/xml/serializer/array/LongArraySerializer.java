package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class LongArraySerializer extends ValueSerializer<long[]> {

	public LongArraySerializer(String name) {
		super(name);
	}

	@Override
	public long[] readValue(String value) {
		ValueArray values = new ValueArray(value);
		long[] array = new long[values.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = Long.parseLong(values.next());
		}
		return array;
	}

	@Override
	public String writeValue(long[] array) {
		boolean comma = false;
		StringBuilder value = new StringBuilder();
		for (long element : array) {
			if (comma) {
				value.append(',');
			}
			comma = true;
			value.append(element);
		}
		return value.toString();
	}
}
