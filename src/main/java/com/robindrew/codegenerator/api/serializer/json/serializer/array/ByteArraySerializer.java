package com.robindrew.codegenerator.api.serializer.json.serializer.array;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class ByteArraySerializer implements IJsonSerializer<byte[]> {

	@Override
	public byte[] readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, byte[] array) {
		if (array == null) {
			writer.writeNull();
			return;
		}
		int index = 0;
		writer.openArray();
		for (byte element : array) {
			writer.writeComma(index++);
			writer.writeByte(element);
		}
		writer.closeArray();
	}

}
