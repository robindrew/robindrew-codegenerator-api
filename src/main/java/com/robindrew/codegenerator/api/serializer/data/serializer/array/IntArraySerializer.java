package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class IntArraySerializer extends ObjectSerializer<int[]> {

	public IntArraySerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	protected int[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = reader.readInt();
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, int[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (int element : array) {
			writer.writeInt(element);
		}
	}

}
