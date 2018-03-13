package com.robindrew.codegenerator.api.serializer.data.serializer.lang;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class ShortSerializer extends ObjectSerializer<Short> {

	public ShortSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public Short readValue(IDataReader reader) throws IOException {
		return reader.readShort();
	}

	@Override
	public void writeValue(IDataWriter writer, Short value) throws IOException {
		writer.writeShort(value);
	}

}
