package com.robindrew.codegenerator.api.serializer.data.serializer.array;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class DoubleArraySerializer extends ObjectSerializer<double[]> {

	public DoubleArraySerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	protected double[] readValue(IDataReader reader) throws IOException {
		int length = reader.readPositiveInteger();
		double[] array = new double[length];
		for (int i = 0; i < length; i++) {
			array[i] = reader.readDouble();
		}
		return array;
	}

	@Override
	protected void writeValue(IDataWriter writer, double[] array) throws IOException {
		writer.writePositiveInteger(array.length);
		for (double element : array) {
			writer.writeDouble(element);
		}
	}

}
