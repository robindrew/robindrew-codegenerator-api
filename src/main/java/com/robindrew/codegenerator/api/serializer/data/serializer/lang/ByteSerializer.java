package com.robindrew.codegenerator.api.serializer.data.serializer.lang;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class ByteSerializer extends ObjectSerializer<Byte> {

	public ByteSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public Byte readValue(IDataReader reader) throws IOException {
		return reader.readByte();
	}

	@Override
	public void writeValue(IDataWriter writer, Byte value) throws IOException {
		writer.writeByte(value);
	}

}
