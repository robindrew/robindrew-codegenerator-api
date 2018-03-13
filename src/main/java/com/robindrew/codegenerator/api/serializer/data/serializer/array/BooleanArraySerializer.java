package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class BooleanArraySerializer extends ObjectSerializer<boolean[]> {

	public BooleanArraySerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	protected boolean[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		boolean[] array = new boolean[length];
		for (int i = 0; i < length; i++) {
			array[i] = reader.readBoolean();
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, boolean[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (boolean element : array) {
			writer.writeBoolean(element);
		}
	}

}
