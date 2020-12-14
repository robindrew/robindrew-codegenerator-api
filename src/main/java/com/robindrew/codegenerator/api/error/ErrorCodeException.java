package com.robindrew.codegenerator.api.error;

public class ErrorCodeException extends RuntimeException implements IErrorCodeException {

	private static final long serialVersionUID = 4954123814864755257L;

	public static final ErrorCodeException error(IErrorCode errorCode) {
		throw new ErrorCodeException(errorCode);
	}

	public static final ErrorCodeException error(IErrorCode errorCode, String key, String value) {
		ErrorCodeSet errors = new ErrorCodeSet();
		errors.add(errorCode).set(key, value);
		throw new ErrorCodeException(errors);
	}

	public static final ErrorCodeException error(IErrorCode errorCode, String key1, String value1, String key2, String value2) {
		ErrorCodeSet errors = new ErrorCodeSet();
		errors.add(errorCode).set(key1, value1).set(key2, value2);
		throw new ErrorCodeException(errors);
	}

	private final IErrorCodeSet errors;

	public ErrorCodeException(IErrorCode error) {
		super(error.name());
		this.errors = new ErrorCodeSet();
		this.errors.add(error);
	}

	public ErrorCodeException(IErrorCodeSet errors) {
		super(errors.getFirstError().name());
		this.errors = errors;
	}

	public ErrorCodeException(IErrorCodeSetContainer container) {
		this(container.getErrors());
	}

	public IErrorCodeSet getErrors() {
		return errors;
	}

}
