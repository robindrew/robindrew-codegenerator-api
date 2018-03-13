package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class ShortArraySerializer extends ObjectSerializer<short[]> {

	public ShortArraySerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	protected short[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		short[] array = new short[length];
		for (int i = 0; i < length; i++) {
			array[i] = reader.readShort();
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, short[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (short element : array) {
			writer.writeShort(element);
		}
	}

}
