package com.robindrew.codegenerator.api.serializer.data;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

public class DataReaderSerializer {

	public IDataReader newReader(DataInput input) {
		return new DataReader(input);
	}

	public <T> T fromData(IDataSerializer<T> serializer, byte[] bytes) throws IOException {
		ByteArrayInputStream input = new ByteArrayInputStream(bytes);
		IDataReader reader = newReader(new DataInputStream(input));
		return serializer.readObject(reader);
	}
}
