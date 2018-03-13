package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class CharArraySerializer extends ObjectSerializer<char[]> {

	public CharArraySerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	protected char[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		char[] array = new char[length];
		for (int i = 0; i < length; i++) {
			array[i] = reader.readChar();
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, char[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (char element : array) {
			writer.writeChar(element);
		}
	}

}
