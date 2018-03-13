package com.robindrew.codegenerator.api.error;

public class ErrorCodeException extends RuntimeException implements IErrorCodeException {

	private static final long serialVersionUID = 4954123814864755257L;

	public static final ErrorCodeException error(IErrorCode errorCode) {
		throw new ErrorCodeException(errorCode);
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
