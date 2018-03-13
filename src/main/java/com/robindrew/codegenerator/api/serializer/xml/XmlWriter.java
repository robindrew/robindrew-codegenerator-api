package com.robindrew.codegenerator.api.serializer.xml;

import static com.robindrew.common.util.Java.propagate;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class XmlWriter implements IXmlWriter {

	public static <O> String toXml(IXmlSerializer<O> serializer, O object, boolean format) {
		StringWriter writer = new StringWriter();
		serializer.writeObject(new XmlWriter(writer).setFormatting(format), object);
		return writer.toString();
	}

	private final Writer writer;
	private String newLine = "\n";
	private String indent = "\t";
	private int indentCount = 0;
	private boolean elementOpen = false;
	private boolean formatting = true;

	public XmlWriter(Writer writer) {
		if (writer == null) {
			throw new NullPointerException("writer");
		}
		this.writer = writer;
	}

	public XmlWriter() {
		this.writer = new StringWriter();
	}

	public Writer getWriter() {
		return writer;
	}

	public String getNewLine() {
		return newLine;
	}

	public boolean isFormatting() {
		return formatting;
	}

	public XmlWriter setFormatting(boolean format) {
		this.formatting = format;
		return this;
	}

	public XmlWriter setNewLine(String newLine) {
		if (newLine.isEmpty()) {
			throw new IllegalArgumentException("newLine");
		}
		this.newLine = newLine;
		return this;
	}

	public String getIndent() {
		return indent;
	}

	public XmlWriter setIndent(String indent) {
		if (indent.isEmpty()) {
			throw new IllegalArgumentException("indent");
		}
		this.indent = indent;
		return this;
	}

	protected void writeIndent() throws IOException {
		if (formatting) {
			for (int i = 0; i < indentCount; i++) {
				writer.write(indent);
			}
		}
	}

	protected void writeNewLine() throws IOException {
		if (formatting) {
			writer.write(newLine);
		}
	}

	protected void closeElement() {
		if (elementOpen) {
			elementOpen = false;
			try {
				writer.write('>');
				writeNewLine();
			} catch (IOException e) {
				throw propagate(e);
			}
		}
	}

	protected String escape(String value, boolean attribute) {
		if (value == null) {
			return "null";
		}
		StringBuilder escaped = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '<') {
				escaped.append("&lt;");
				continue;
			}
			if (c == '>') {
				escaped.append("&gt;");
				continue;
			}
			if (c == '&') {
				escaped.append("&amp;");
				continue;
			}
			if (attribute) {
				if (c == '\'') {
					escaped.append("&apos;");
					continue;
				}
				if (c == '\"') {
					escaped.append("&quot;");
					continue;
				}
			}
			escaped.append(c);
		}
		return escaped.toString();
	}

	protected String escape(Object value, boolean attribute) {
		return value == null ? "null" : escape(value.toString(), attribute);
	}

	@Override
	public void emptyElement(String name) {
		try {
			closeElement();
			writeIndent();
			writer.write('<');
			writer.write(name);
			writer.write('/');
			writer.write('>');
			writeNewLine();
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	@Override
	public void startElement(String name) {
		try {
			closeElement();
			writeIndent();
			writer.write('<');
			writer.write(name);
			elementOpen = true;
			indentCount++;
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	@Override
	public void endElement(String name) {
		try {
			closeElement();
			indentCount--;
			writeIndent();
			writer.write('<');
			writer.write('/');
			writer.write(name);
			writer.write('>');
			writeNewLine();
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	@Override
	public void writeAttribute(String name, Object value) {
		if (!elementOpen) {
			throw new IllegalStateException("element not open");
		}
		try {
			writer.write(' ');
			writer.write(name);
			writer.write('=');
			writer.write('\"');
			writer.write(escape(value, true));
			writer.write('\"');
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	@Override
	public <V> void writeAttribute(String name, V value, IXmlValueSerializer<V> serializer) {
		String text = serializer.writeValue(value);
		writeAttribute(name, text);
	}

	@Override
	public void writeElement(String name, Object value) {
		if (value == null) {
			emptyElement(name);
			return;
		}
		try {
			closeElement();
			writeIndent();
			writer.write('<');
			writer.write(name);
			writer.write('>');
			writer.write(escape(value, false));
			writer.write('<');
			writer.write('/');
			writer.write(name);
			writer.write('>');
			writeNewLine();
		} catch (IOException e) {
			throw propagate(e);
		}
	}

	@Override
	public <V> void writeElement(String name, V value, IXmlValueSerializer<V> serializer) {
		String text = serializer.writeValue(value);
		writeElement(name, text);
	}

	@Override
	public void writeBoolean(String name, boolean value) {
		writeElement(name, String.valueOf(value));
	}

	@Override
	public void writeChar(String name, char value) {
		writeElement(name, String.valueOf(value));
	}

	@Override
	public void writeByte(String name, byte value) {
		writeElement(name, String.valueOf(value));
	}

	@Override
	public void writeShort(String name, short value) {
		writeElement(name, String.valueOf(value));
	}

	@Override
	public void writeInt(String name, int value) {
		writeElement(name, String.valueOf(value));
	}

	@Override
	public void writeLong(String name, long value) {
		writeElement(name, String.valueOf(value));
	}

	@Override
	public void writeFloat(String name, float value) {
		writeElement(name, String.valueOf(value));
	}

	@Override
	public void writeDouble(String name, double value) {
		writeElement(name, String.valueOf(value));
	}

	@Override
	public <O> void writeObject(O object, IXmlSerializer<O> serializer) {
		serializer.writeObject(this, object);
	}

}
