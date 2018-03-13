package com.robindrew.codegenerator.api.serializer.json.serializer.array;

import org.apache.commons.codec.binary.Base64;

import com.robindrew.codegenerator.api.serializer.json.IJsonReader;
import com.robindrew.codegenerator.api.serializer.json.IJsonSerializer;
import com.robindrew.codegenerator.api.serializer.json.IJsonWriter;

public class Base64Serializer implements IJsonSerializer<byte[]> {

	@Override
	public byte[] readObject(IJsonReader reader) {
		throw new UnsupportedOperationException("readObject");
	}

	@Override
	public void writeObject(IJsonWriter writer, byte[] array) {
		if (array == null) {
			writer.writeNull();
			return;
		}
		String text = Base64.encodeBase64String(array);
		writer.writeString(text);
	}

}
