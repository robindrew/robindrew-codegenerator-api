package com.robindrew.codegenerator.api.serializer.json.serializer.array;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class CharArraySerializer implements IJsonSerializer<char[]> {

	@Override
	public char[] readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, char[] array) {
		if (array == null) {
			writer.writeNull();
			return;
		}
		int index = 0;
		writer.openArray();
		for (char element : array) {
			writer.writeComma(index++);
			writer.writeChar(element);
		}
		writer.closeArray();
	}

}
