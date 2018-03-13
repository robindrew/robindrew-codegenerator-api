package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class BooleanSerializer implements IJsonSerializer<Boolean> {

	@Override
	public Boolean readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Boolean value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeBoolean(value);
	}

}
