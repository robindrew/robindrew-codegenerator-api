package com.robindrew.codegenerator.api.serializer.json;

public interface IJsonSerializer<O> {

	O readObject(IJsonReader reader);

	void writeObject(IJsonWriter writer, O object);
}
