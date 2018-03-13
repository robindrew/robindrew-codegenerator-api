package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class ShortSerializer implements IJsonSerializer<Short> {

	@Override
	public Short readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Short value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeShort(value);
	}

}
