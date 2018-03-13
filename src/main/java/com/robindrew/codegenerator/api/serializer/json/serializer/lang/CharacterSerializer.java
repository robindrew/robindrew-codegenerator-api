package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class CharacterSerializer implements IJsonSerializer<Character> {

	@Override
	public Character readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Character value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeChar(value);
	}

}
