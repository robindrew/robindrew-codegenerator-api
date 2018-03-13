package com.robindrew.codegenerator.api.serializer.data.serializer.lang;

import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.base.Charsets;
import com.robindrew.codegenerator.api.serializer.data.IDataReader;
import com.robindrew.codegenerator.api.serializer.data.IDataWriter;
import com.robindrew.codegenerator.api.serializer.data.serializer.ObjectSerializer;

public class StringSerializer extends ObjectSerializer<String> {

	private final Charset charset;

	public StringSerializer(Charset charset, boolean nullable) {
		super(nullable);
		if (charset == null) {
			throw new NullPointerException("charset");
		}
		this.charset = charset;
	}

	public StringSerializer(boolean nullable) {
		this(Charsets.UTF_8, nullable);
	}

	@Override
	public String readValue(IDataReader reader) throws IOException {
		return reader.readString(charset);
	}

	@Override
	public void writeValue(IDataWriter writer, String value) throws IOException {
		writer.writeString(value, charset);
	}

}
