package com.robindrew.codegenerator.api.serializer.json.serializer.array;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class LongArraySerializer implements IJsonSerializer<long[]> {

	@Override
	public long[] readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, long[] array) {
		if (array == null) {
			writer.writeNull();
			return;
		}
		int index = 0;
		writer.openArray();
		for (long element : array) {
			writer.writeComma(index++);
			writer.writeLong(element);
		}
		writer.closeArray();
	}

}
