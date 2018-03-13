package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class FloatArraySerializer extends ObjectSerializer<float[]> {

	public FloatArraySerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	protected float[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		float[] array = new float[length];
		for (int i = 0; i < length; i++) {
			array[i] = reader.readFloat();
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, float[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (float element : array) {
			writer.writeFloat(element);
		}
	}

}
