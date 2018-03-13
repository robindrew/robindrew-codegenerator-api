package com.robindrew.codegenerator.api.serializer.data.serializer.lang;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class DoubleSerializer extends ObjectSerializer<Double> {

	public DoubleSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public Double readValue(IDataReader reader) throws IOException {
		return reader.readDouble();
	}

	@Override
	public void writeValue(IDataWriter writer, Double value) throws IOException {
		writer.writeDouble(value);
	}

}
