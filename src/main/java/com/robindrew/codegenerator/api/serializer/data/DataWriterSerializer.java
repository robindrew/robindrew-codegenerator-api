package com.robindrew.codegenerator.api.serializer.data;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class DataWriterSerializer {

	public IDataWriter newWriter(DataOutput output) {
		return new DataWriter(output);
	}

	public <T> byte[] toData(IDataSerializer<T> serializer, T object) throws IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutput output = new DataOutputStream(bytes);
		serializer.writeObject(newWriter(output), object);
		return bytes.toByteArray();
	}
}
