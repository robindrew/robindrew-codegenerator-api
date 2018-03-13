package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class ByteSerializer implements IJsonSerializer<Byte> {

	@Override
	public Byte readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Byte value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeByte(value);
	}

}
