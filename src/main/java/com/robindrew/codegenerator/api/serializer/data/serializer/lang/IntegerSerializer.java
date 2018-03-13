package com.robindrew.codegenerator.api.serializer.data.serializer.lang;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class IntegerSerializer extends ObjectSerializer<Integer> {

	public IntegerSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public Integer readValue(IDataReader reader) throws IOException {
		return reader.readInt();
	}

	@Override
	public void writeValue(IDataWriter writer, Integer value) throws IOException {
		writer.writeInt(value);
	}

}
