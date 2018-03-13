package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class DoubleSerializer implements IJsonSerializer<Double> {

	@Override
	public Double readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Double value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeDouble(value);
	}

}
