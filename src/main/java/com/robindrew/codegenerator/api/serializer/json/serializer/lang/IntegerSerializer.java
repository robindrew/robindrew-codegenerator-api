package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class IntegerSerializer implements IJsonSerializer<Integer> {

	@Override
	public Integer readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Integer value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeInt(value);
	}

}
