package com.robindrew.codegenerator.api.serializer.data;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.base.Charsets;

public class DataWriter implements IDataWriter {

	private final DataOutput output;

	public DataWriter(DataOutput output) {
		if (output == null) {
			throw new NullPointerException("output");
		}
		this.output = output;
	}

	@Override
	public void write(int b) throws IOException {
		output.write(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		output.write(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		output.write(b, off, len);
	}

	@Override
	public void writeBoolean(boolean v) throws IOException {
		output.writeBoolean(v);
	}

	@Override
	public void writeByte(int v) throws IOException {
		output.writeByte(v);
	}

	@Override
	public void writeShort(int v) throws IOException {
		output.writeShort(v);
	}

	@Override
	public void writeChar(int v) throws IOException {
		output.writeChar(v);
	}

	@Override
	public void writeInt(int v) throws IOException {
		output.writeInt(v);
	}

	@Override
	public void writeLong(long v) throws IOException {
		output.writeLong(v);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		output.writeFloat(v);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		output.writeDouble(v);
	}

	@Override
	public void writeBytes(String s) throws IOException {
		output.writeBytes(s);
	}

	@Override
	public void writeChars(String s) throws IOException {
		output.writeChars(s);
	}

	@Override
	public void writeUTF(String s) throws IOException {
		output.writeUTF(s);
	}

	@Override
	public void writeNull(boolean isNull) throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void writePositiveByte(byte value) throws IOException {
		writePositiveLong(value);
	}

	@Override
	public void writePositiveShort(short value) throws IOException {
		writePositiveLong(value);
	}

	@Override
	public void writePositiveInteger(int value) throws IOException {
		writePositiveLong(value);
	}

	@Override
	public void writePositiveLong(long value) throws IOException {
		if (value < 0) {
			throw new IllegalArgumentException("value=" + value);
		}
		if (value == 0) {
			write((byte) 0);
			return;
		}
		do {
			byte write = (byte) (value & 127l);
			value >>= 7;
			if (value > 0) {
				write |= -128;
			}
			write(write);
		} while (value > 0);
	}

	@Override
	public void writeByteArray(byte[] array) throws IOException {
		writePositiveInteger(array.length);
		for (byte element : array) {
			writeByte(element);
		}
	}

	@Override
	public void writeString(String text) throws IOException {
		writeString(text, Charsets.UTF_8);
	}

	@Override
	public void writeString(String text, Charset charset) throws IOException {
		byte[] array = text.getBytes(charset);
		writeByteArray(array);
	}

	@Override
	public <O> void writeObject(O object, IDataSerializer<O> serializer) throws IOException {
		serializer.writeObject(this, object);
	}

}
