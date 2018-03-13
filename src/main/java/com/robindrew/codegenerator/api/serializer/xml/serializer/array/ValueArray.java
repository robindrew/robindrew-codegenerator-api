package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import java.util.Iterator;

public class ValueArray implements Iterator<String> {

	private static final char SEPARATOR = ',';

	private final String text;
	private int length = -1;
	private int index = 0;
	private boolean hasNext = true;

	public ValueArray(String text) {
		this.text = text;
	}

	public int length() {
		if (length == -1) {
			int commas = 0;
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == SEPARATOR) {
					commas++;
				}
			}
			length = commas + 1;
		}
		return length;
	}

	@Override
	public boolean hasNext() {
		return hasNext;
	}

	@Override
	public String next() {
		if (!hasNext) {
			throw new IllegalStateException("no more tokens in array");
		}
		int commaIndex = text.indexOf(SEPARATOR, index);
		if (commaIndex == -1) {
			hasNext = false;
			commaIndex = text.length();
		}
		String next = text.substring(index, commaIndex);
		index = commaIndex + 1;
		return next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
