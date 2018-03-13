package com.robindrew.codegenerator.api.serializer.xml.serializer.array;

import com.robindrew.codegenerator.api.serializer.xml.serializer.ValueSerializer;

public class DoubleArraySerializer extends ValueSerializer<double[]> {

	public DoubleArraySerializer(String name) {
		super(name);
	}

	@Override
	public double[] readValue(String value) {
		ValueArray values = new ValueArray(value);
		double[] array = new double[values.length()];
		for (int i = 0; i < array.length; i++) {
			array[i] = Float.parseFloat(values.next());
		}
		return array;
	}

	@Override
	public String writeValue(double[] array) {
		boolean comma = false;
		StringBuilder value = new StringBuilder();
		for (double element : array) {
			if (comma) {
				value.append(',');
			}
			comma = true;
			value.append(element);
		}
		return value.toString();
	}
}
