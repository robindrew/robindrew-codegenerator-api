package com.robindrew.codegenerator.api.serializer.data.serializer.lang;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class LongSerializer extends ObjectSerializer<Long> {

	public LongSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public Long readValue(IDataReader reader) throws IOException {
		return reader.readLong();
	}

	@Override
	public void writeValue(IDataWriter writer, Long value) throws IOException {
		writer.writeLong(value);
	}

}
