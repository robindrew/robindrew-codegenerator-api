package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class ByteArraySerializer extends ObjectSerializer<byte[]> {

	public ByteArraySerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	protected byte[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		byte[] array = new byte[length];
		for (int i = 0; i < length; i++) {
			array[i] = reader.readByte();
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, byte[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (byte element : array) {
			writer.writeByte(element);
		}
	}

}
