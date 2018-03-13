package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import org.apache.commons.codec.binary.Base64;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class Base64Serializer extends ValueSerializer<byte[]> {

	public Base64Serializer(String name) {
		super(name);
	}

	@Override
	public String writeValue(byte[] array) {
		return Base64.encodeBase64String(array);
	}

	@Override
	public byte[] readValue(String text) {
		return Base64.decodeBase64(text);
	}

}
