package com.robindrew.codegenerator.api.sql.builder.mysql;

import org.apache.commons.codec.binary.Hex;

import com.robindrew.codegenerator.api.sql.builder.ISqlBuilder;
import com.robindrew.codegenerator.api.sql.builder.SqlBuilder;

public class MysqlSqlBuilder extends SqlBuilder implements IMysqlSqlBuilder {

	@Override
	protected String escape(String value) {
		if (!requiresEscape(value)) {
			return value;
		}

		// MySQL escapes single quotes with a backslash,
		// NOT another single quote
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '\\' || c == '\'') {
				builder.append('\\');
			}
			builder.append(c);
		}
		return builder.toString();
	}

	private boolean requiresEscape(String value) {
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '\'') {
				return true;
			}
			if (c == '\\') {
				return true;
			}
		}
		return false;
	}

	@Override
	public ISqlBuilder table(String name) {
		return sql('`').sql(name).sql('`');
	}

	@Override
	public ISqlBuilder column(String name) {
		return sql('`').sql(name).sql('`');
	}

	@Override
	public ISqlBuilder value(byte[] value) {
		if (value.length == 0) {
			sql("''");
		} else {
			char[] hex = Hex.encodeHex(value);
			sql('0').sql('x').sql(hex);
		}
		return this;
	}

}
