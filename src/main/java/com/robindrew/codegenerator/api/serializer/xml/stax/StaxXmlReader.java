package com.robindrew.codegenerator.api.serializer.xml.stax;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.robindrew.codegenerator.api.serializer.xml.IXmlReader;
import com.robindrew.codegenerator.api.serializer.xml.IXmlSerializer;
import com.robindrew.codegenerator.api.serializer.xml.IXmlValueSerializer;
import com.robindrew.common.util.Java;

public class StaxXmlReader implements IXmlReader {

	public static StaxXmlReader newInstance(String xml) {
		return newInstance(new StringReader(xml));
	}

	public static StaxXmlReader newInstance(Reader reader) {
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(reader);
			return new StaxXmlReader(eventReader);
		} catch (Exception e) {
			throw Java.propagate(e);
		}
	}

	private final XMLEventReader reader;
	private Iterator<Attribute> attributes = null;
	private boolean attributesPopulated = false;
	private Map<String, String> attributeMap = new HashMap<>();

	public StaxXmlReader(XMLEventReader reader) {
		if (reader == null) {
			throw new NullPointerException("reader");
		}
		this.reader = reader;
	}

	private void clearAttributes() {
		attributes = null;
		attributeMap.clear();
		attributesPopulated = false;
	}

	private Map<String, String> getAttributeMap() {
		if (attributes == null) {
			throw new IllegalStateException("attributes not available at this point");
		}
		if (!attributesPopulated) {
			attributesPopulated = true;
			while (attributes.hasNext()) {
				Attribute attribute = attributes.next();
				String key = attribute.getName().toString();
				String value = attribute.getValue();
				attributeMap.put(key, value);
			}
		}
		return attributeMap;

	}

	private char parseChar(String text) {
		if (text.length() != 1) {
			throw new IllegalStateException("unable to parse a character from text: '" + text + "'");
		}
		return text.charAt(0);
	}

	private XMLEvent nextEvent() {
		try {
			XMLEvent event = reader.nextEvent();
			int type = event.getEventType();
			if (type == XMLEvent.START_DOCUMENT) {
				event = reader.nextEvent();
			}
			return event;
		} catch (XMLStreamException e) {
			throw Java.propagate(e);
		}
	}

	private XMLEvent nextEvent(int expectedType, boolean skipWhitespace) {
		XMLEvent event = nextEvent();
		if (skipWhitespace && event.getEventType() == XMLEvent.CHARACTERS) {
			String text = event.asCharacters().getData();
			if (!text.trim().isEmpty()) {
				throw new IllegalStateException("Attempted to skip whitspace, but text: '" + text + "'");
			}
			event = nextEvent();
		}
		if (event.getEventType() != expectedType) {
			throw new IllegalStateException("Unexpected event=" + event);
		}
		return event;
	}

	@Override
	public boolean emptyElement() {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void startElement(String name) {
		StartElement start = nextEvent(XMLEvent.START_ELEMENT, true).asStartElement();
		if (!start.getName().toString().equals(name)) {
			throw new IllegalStateException("Unexpected event=" + start + ", expected startElement('" + name + "')");
		}
		clearAttributes();
		attributes = start.getAttributes();
	}

	@Override
	public void endElement(String name) {
		EndElement end = nextEvent(XMLEvent.END_ELEMENT, true).asEndElement();
		if (!end.getName().toString().equals(name)) {
			throw new IllegalStateException("Unexpected event=" + end + ", expected startElement('" + name + "')");
		}
		clearAttributes();
	}

	@Override
	public String readElement(String name) {
		startElement(name);

		// Parse the text - could be an end element if empty ...
		StringBuilder text = new StringBuilder();
		XMLEvent event = nextEvent();
		while (event.getEventType() == XMLEvent.CHARACTERS) {
			text.append(event.asCharacters().getData());
			event = nextEvent();
		}

		// End element
		if (event.getEventType() != XMLEvent.END_ELEMENT) {
			throw new IllegalStateException("Unexpected event=" + event);
		}
		EndElement end = event.asEndElement();
		if (!end.getName().toString().equals(name)) {
			throw new IllegalStateException("Unexpected event=" + end + ", expected startElement('" + name + "')");
		}

		clearAttributes();
		return text.toString();
	}

	@Override
	public <V> V readElement(String name, IXmlValueSerializer<V> serializer) {
		String value = readElement(name);
		return serializer.readValue(value);
	}

	@Override
	public String readAttribute(String name) {
		String value = getAttributeMap().get(name);
		if (value == null) {
			throw new IllegalStateException("Missing attribute: " + name);
		}
		return value;
	}

	@Override
	public <V> V readAttribute(String name, IXmlValueSerializer<V> serializer) {
		String value = readAttribute(name);
		return serializer.readValue(value);
	}

	@Override
	public boolean hasAttribute(String name) {
		return getAttributeMap().containsKey(name);
	}

	@Override
	public boolean readBoolean(String name) {
		String value = readElement(name);
		return Boolean.parseBoolean(value);
	}

	@Override
	public char readChar(String name) {
		String value = readElement(name);
		return parseChar(value);
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
	public char readCharAttribute(String name) {
		String value = readAttribute(name);
		return parseChar(value);
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
	public float readFloatAttribute(String name) {
		String value = readAttribute(name);
		return Float.parseFloat(value);
	}

	@Override
	public double readDoubleAttribute(String name) {
		String value = readAttribute(name);
		return Double.parseDouble(value);
	}

	@Override
	public boolean readBooleanAttribute(String name) {
		String value = readAttribute(name);
		return Boolean.parseBoolean(value);
	}

	@Override
	public <O> O readObject(IXmlSerializer<O> serializer) {
		return serializer.readObject(this);
	}

}
