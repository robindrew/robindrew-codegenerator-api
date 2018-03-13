package com.robindrew.codegenerator.api.serializer.json.serializer.array;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class FloatArraySerializer implements IJsonSerializer<float[]> {

	@Override
	public float[] readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, float[] array) {
		if (array == null) {
			writer.writeNull();
			return;
		}
		int index = 0;
		writer.openArray();
		for (float element : array) {
			writer.writeComma(index++);
			writer.writeFloat(element);
		}
		writer.closeArray();
	}

}
