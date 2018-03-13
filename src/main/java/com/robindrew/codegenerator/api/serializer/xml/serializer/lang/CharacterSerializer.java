package com.robindrew.codegenerator.api.serializer.xml.serializer.lang;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class CharacterSerializer extends ValueSerializer<Character> {

	public CharacterSerializer(String name) {
		super(name);
	}

	@Override
	public Character readValue(String value) {
		return value.charAt(0);
	}
}
