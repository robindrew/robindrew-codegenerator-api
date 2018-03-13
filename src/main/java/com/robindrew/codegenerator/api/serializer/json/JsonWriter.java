package com.robindrew.codegenerator.api.serializer.json;

import static com.robindrew.common.util.Java.propagate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class JsonWriter implements IJsonWriter {

	private final Writer writer;

	public JsonWriter(Writer writer) {
		if (writer == null) {
			throw new NullPointerException("writer");
		}
		this.writer = writer;
	}

	public JsonWriter() {
		this.writer = new StringWriter();
	}

	public Writer getWriter() {
		return writer;
	}

	protected void write(char value) {
		try {
			writer.write(value);
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	protected void write(String value) {
		try {
			writer.write(value);
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	protected String escape(String value) {
		if (value == null) {
			return null;
		}
		StringBuilder escaped = new StringBuilder();
		escaped.append('\"');
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '\\' || c == '\'') {
				escaped.append('\\');
			}
			escaped.append(c);
		}
		escaped.append('\"');
		return escaped.toString();
	}

	protected void write(String name, String value, boolean comma) {
		name = escape(name);
		try {
			writer.write(name);
			writer.write(':');
			writer.write(value);
			if (comma) {
				writeComma();
			}
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	@Override
	public void openObject() {
		write('{');
	}

	@Override
	public void closeObject() {
		write('}');
	}

	@Override
	public void openArray() {
		write('[');
	}

	@Override
	public void closeArray() {
		write(']');
	}

	@Override
	public void writeNull() {
		write("null");
	}

	@Override
	public void writeComma() {
		write(',');
	}

	@Override
	public void writeComma(int index) {
		if (index > 0) {
			write(',');
		}
	}

	@Override
	public void writeName(String name) {
		name = escape(name);
		try {
			writer.write(name);
			writer.write(':');
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	@Override
	public void writeBoolean(boolean value) {
		write(String.valueOf(value));
	}

	@Override
	public void writeChar(char value) {
		write(String.valueOf(value));
	}

	@Override
	public void writeByte(byte value) {
		write(String.valueOf(value));
	}

	@Override
	public void writeShort(short value) {
		write(String.valueOf(value));
	}

	@Override
	public void writeInt(int value) {
		write(String.valueOf(value));
	}

	@Override
	public void writeLong(long value) {
		write(String.valueOf(value));
	}

	@Override
	public void writeFloat(float value) {
		write(String.valueOf(value));
	}

	@Override
	public void writeDouble(double value) {
		write(String.valueOf(value));
	}

	@Override
	public void writeString(String value) {
		write(escape(value));
	}

	@Override
	public void writeNull(String name, boolean comma) {
		write(name, "null", comma);
	}

	@Override
	public void writeBoolean(String name, boolean value, boolean comma) {
		write(name, value ? "true" : "false", comma);
	}

	@Override
	public void writeChar(String name, char value, boolean comma) {
		write(name, String.valueOf(value), comma);
	}

	@Override
	public void writeByte(String name, byte value, boolean comma) {
		write(name, String.valueOf(value), comma);
	}

	@Override
	public void writeShort(String name, short value, boolean comma) {
		write(name, String.valueOf(value), comma);
	}

	@Override
	public void writeInt(String name, int value, boolean comma) {
		write(name, String.valueOf(value), comma);
	}

	@Override
	public void writeLong(String name, long value, boolean comma) {
		write(name, String.valueOf(value), comma);
	}

	@Override
	public void writeFloat(String name, float value, boolean comma) {
		write(name, String.valueOf(value), comma);
	}

	@Override
	public void writeDouble(String name, double value, boolean comma) {
		write(name, String.valueOf(value), comma);
	}

	@Override
	public void writeString(String name, String value, boolean comma) {
		write(name, escape(value), comma);
	}

	@Override
	public <O> void writeObject(O value, IJsonSerializer<O> serializer) {
		serializer.writeObject(this, value);
	}

	@Override
	public <O> void writeObject(String name, O value, IJsonSerializer<O> serializer, boolean comma) {
		writeName(name);
		serializer.writeObject(this, value);
		if (comma) {
			writeComma();
		}
	}

}
