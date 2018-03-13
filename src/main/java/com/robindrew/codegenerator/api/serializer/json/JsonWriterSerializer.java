package com.robindrew.codegenerator.api.serializer.json;

import java.io.StringWriter;
import java.io.Writer;

public class JsonWriterSerializer {

	public IJsonWriter newWriter(Writer writer) {
		return new JsonWriter(writer);
	}

	public <T> String toJson(IJsonSerializer<T> serializer, T object) {
		StringWriter writer = new StringWriter();
		serializer.writeObject(newWriter(writer), object);
		return writer.toString();
	}
}
