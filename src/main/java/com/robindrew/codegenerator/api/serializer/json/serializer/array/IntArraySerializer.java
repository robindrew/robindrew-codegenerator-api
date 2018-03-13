package com.robindrew.codegenerator.api.serializer.json.serializer.array;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class IntArraySerializer implements IJsonSerializer<int[]> {

	@Override
	public int[] readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, int[] array) {
		if (array == null) {
			writer.writeNull();
			return;
		}
		int index = 0;
		writer.openArray();
		for (int element : array) {
			writer.writeComma(index++);
			writer.writeInt(element);
		}
		writer.closeArray();
	}

}
