package com.robindrew.codegenerator.api.serializer.json.serializer.lang;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class FloatSerializer implements IJsonSerializer<Float> {

	@Override
	public Float readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, Float value) {
		if (value == null) {
			writer.writeNull();
			return;
		}
		writer.writeFloat(value);
	}

}
