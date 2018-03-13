package com.robindrew.codegenerator.api.serializer.json;

public interface IJsonValueSerializer<O> {

	O readValue(String text);

	String writeValue(O object);
}
