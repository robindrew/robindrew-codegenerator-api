package com.robindrew.codegenerator.api.serializer.data;

import java.io.DataInput;
import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.base.Charsets;

public class DataReader implements IDataReader {

	private final DataInput input;

	public DataReader(DataInput input) {
		if (input == null) {
			throw new NullPointerException("input");
		}
		this.input = input;
	}

	public void readFully(byte[] b) throws IOException {
		input.readFully(b);
	}

	public void readFully(byte[] b, int off, int len) throws IOException {
		input.readFully(b, off, len);
	}

	public int skipBytes(int n) throws IOException {
		return input.skipBytes(n);
	}

	public boolean readBoolean() throws IOException {
		return input.readBoolean();
	}

	public byte readByte() throws IOException {
		return input.readByte();
	}

	public int readUnsignedByte() throws IOException {
		return input.readUnsignedByte();
	}

	public short readShort() throws IOException {
		return input.readShort();
	}

	public int readUnsignedShort() throws IOException {
		return input.readUnsignedShort();
	}

	public char readChar() throws IOException {
		return input.readChar();
	}

	public int readInt() throws IOException {
		return input.readInt();
	}

	public long readLong() throws IOException {
		return input.readLong();
	}

	public float readFloat() throws IOException {
		return input.readFloat();
	}

	public double readDouble() throws IOException {
		return input.readDouble();
	}

	public String readLine() throws IOException {
		return input.readLine();
	}

	public String readUTF() throws IOException {
		return input.readUTF();
	}

	@Override
	public boolean readNull() throws IOException {
		throw new UnsupportedOperationException();
	}

	private int readAndCheck() throws IOException {
		int read = input.readByte();
		if (read == -1) {
			throw new IOException("End of stream");
		}
		return read;
	}

	@Override
	public long readPositiveLong() throws IOException {
		int count = 0;
		long number = 0;
		long read = 0;
		do {
			read = readAndCheck();
			number |= (read & 127l) << (count * 7);
			count++;
		} while ((read & -128l) != 0);
		if (number < 0) {
			throw new IOException("read=" + number);
		}
		return number;
	}

	@Override
	public int readPositiveInteger() throws IOException {
		long read = readPositiveLong();
		if (read > Integer.MAX_VALUE) {
			throw new IOException("read=" + read);
		}
		return (int) read;
	}

	@Override
	public short readPositiveShort() throws IOException {
		long read = readPositiveLong();
		if (read > Short.MAX_VALUE) {
			throw new IOException("read=" + read);
		}
		return (short) read;
	}

	@Override
	public byte readPositiveByte() throws IOException {
		long read = readPositiveLong();
		if (read > Byte.MAX_VALUE) {
			throw new IOException("read=" + read);
		}
		return (byte) read;
	}

	@Override
	public byte[] readByteArray() throws IOException {
		int length = readPositiveInteger();
		byte[] array = new byte[length];
		readFully(array, 0, length);
		return array;
	}

	@Override
	public String readString(Charset charset) throws IOException {
		byte[] array = readByteArray();
		return new String(array, charset);
	}

	@Override
	public String readString() throws IOException {
		return readString(Charsets.UTF_8);
	}

	@Override
	public <O> O readObject(IDataSerializer<O> serializer) throws IOException {
		return serializer.readObject(this);
	}

}
