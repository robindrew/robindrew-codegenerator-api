package com.robindrew.codegenerator.api.serializer.xml.dom;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlValueSerializer;
import com.robindrew.common.util.Java;

public class DomXmlReader implements IXmlReader {

	public static DomXmlReader newInstance(String xml) {
		return newInstance(new StringReader(xml));
	}

	public static DomXmlReader newInstance(Reader reader) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(reader));
			return new DomXmlReader(document);
		} catch (Exception e) {
			throw Java.propagate(e);
		}
	}

	private final List<DomNode> elementList = new ArrayList<DomNode>();
	private int depth = 0;

	public DomXmlReader(Document document) {
		Element element = document.getDocumentElement();
		elementList.add(new DomNode(element, true));
	}

	private void removeCurrentNode() {
		elementList.remove(depth);
		depth = elementList.size() - 1;
	}

	private DomNode getCurrentNode() {
		return elementList.get(depth);
	}

	private void setCurrentNode(DomNode node) {
		elementList.add(node);
		depth = elementList.size() - 1;
	}

	@Override
	public boolean emptyElement() {
		DomNode node = getCurrentNode();
		return node.isEmpty();
	}

	@Override
	public void startElement(String name) {
		DomNode node = getCurrentNode();
		DomNode child = node.nextChild(name);
		setCurrentNode(child);
	}

	@Override
	public void endElement(String name) {
		DomNode node = getCurrentNode();
		if (!node.getName().equals(name)) {
			throw new IllegalArgumentException("Expected <" + name + "> found <" + node.getName() + ">");
		}
		removeCurrentNode();
	}

	@Override
	public String readElement(String name) {
		DomNode node = getCurrentNode();
		DomNode child = node.nextChild(name);
		return child.getValue();
	}

	@Override
	public <V> V readElement(String name, IXmlValueSerializer<V> serializer) {
		String value = readElement(name);
		return serializer.readValue(value);
	}

	@Override
	public String readAttribute(String name) {
		DomNode node = getCurrentNode();
		return node.getAttribute(name);
	}

	@Override
	public <V> V readAttribute(String name, IXmlValueSerializer<V> serializer) {
		String value = readAttribute(name);
		return serializer.readValue(value);
	}

	@Override
	public boolean hasAttribute(String name) {
		return getCurrentNode().hasAttribute(name);
	}

	@Override
	public boolean readBoolean(String name) {
		String value = readElement(name);
		return Boolean.parseBoolean(value);
	}

	@Override
	public char readChar(String name) {
		String value = readElement(name);
		if (value.length() != 1) {
			throw new IllegalStateException("Expected char valur for <" + name + "> but found: '" + value + "'");
		}
		return value.charAt(0);
	}

	@Override
	public byte readByte(String name) {
		String value = readElement(name);
		return Byte.parseByte(value);
	}

	@Override
	public short readShort(String name) {
		String value = readElement(name);
		return Short.parseShort(value);
	}

	@Override
	public int readInt(String name) {
		String value = readElement(name);
		return Integer.parseInt(value);
	}

	@Override
	public long readLong(String name) {
		String value = readElement(name);
		return Long.parseLong(value);
	}

	@Override
	public float readFloat(String name) {
		String value = readElement(name);
		return Float.parseFloat(value);
	}

	@Override
	public double readDouble(String name) {
		String value = readElement(name);
		return Double.parseDouble(value);
	}

	@Override
	public <O> O readObject(IXmlSerializer<O> serializer) {
		return serializer.readObject(this);
	}

	@Override
	public char readCharAttribute(String name) {
		String value = readAttribute(name);
		if (value.length() != 1) {
			throw new IllegalArgumentException("Attribute '" + name + "' not a char: '" + value + "'");
		}
		return value.charAt(0);
	}

	@Override
	public byte readByteAttribute(String name) {
		String value = readAttribute(name);
		return Byte.parseByte(value);
	}

	@Override
	public short readShortAttribute(String name) {
		String value = readAttribute(name);
		return Short.parseShort(value);
	}

	@Override
	public int readIntAttribute(String name) {
		String value = readAttribute(name);
		return Integer.parseInt(value);
	}

	@Override
	public long readLongAttribute(String name) {
		String value = readAttribute(name);
		return Long.parseLong(value);
	}

	@Override
	public boolean readBooleanAttribute(String name) {
		String value = readAttribute(name);
		return Boolean.parseBoolean(value);
	}

	@Override
	public float readFloatAttribute(String name) {
		String value = readAttribute(name);
		return Float.parseFloat(value);
	}

	@Override
	public double readDoubleAttribute(String name) {
		String value = readAttribute(name);
		return Double.parseDouble(value);
	}

}
