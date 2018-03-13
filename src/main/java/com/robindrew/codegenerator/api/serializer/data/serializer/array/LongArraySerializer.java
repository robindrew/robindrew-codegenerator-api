package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class LongArraySerializer extends ObjectSerializer<long[]> {

	public LongArraySerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	protected long[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		long[] array = new long[length];
		for (int i = 0; i < length; i++) {
			array[i] = reader.readLong();
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, long[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (long element : array) {
			writer.writeLong(element);
		}
	}

}
