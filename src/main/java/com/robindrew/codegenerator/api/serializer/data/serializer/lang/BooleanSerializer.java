package com.robindrew.codegenerator.api.serializer.data.serializer.lang;

import java.io.IOException;

import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class BooleanSerializer extends ObjectSerializer<Boolean> {

	public BooleanSerializer(boolean nullable) {
		super(nullable);
	}

	@Override
	public Boolean readValue(IDataReader reader) throws IOException {
		return reader.readBoolean();
	}

	@Override
	public void writeValue(IDataWriter writer, Boolean value) throws IOException {
		writer.writeBoolean(value);
	}

}
