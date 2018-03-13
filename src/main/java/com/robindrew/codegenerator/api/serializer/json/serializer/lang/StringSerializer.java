package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class StringSerializer implements IJsonSerializer<String> {

	@Override
	public String readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, String value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeString(value);
	}

}
