package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class ByteArraySerializer extends ValueSerializer<byte[]> {

	public ByteArraySerializer(String name) {
		super(name);
	}

	@Override
	public byte[] readValue(String value) {
		if (value.isEmpty()) {
			return null;
		}
		ValueArray values = new ValueArray(value);
		byte[] array = new byte[values.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = Byte.parseByte(values.next());
		}
		return array;
	}

	@Override
	public String writeValue(byte[] array) {
		boolean comma = false;
		StringBuilder value = new StringBuilder();
		for (byte element : array) {
			if (comma) {
				value.append(',');
			}
			comma = true;
			value.append(element);
		}
		return value.toString();
	}
}
