package com.robindrew.codegenerator.api.executable.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.common.annotations.GwtCompatible;
import com.robindrew.codegenerator.api.error.IErrorCodeSet;

@GwtCompatible
public class ExecutableBeanResponse<R> implements IExecutableBeanResponse<R>, Serializable {

	private static final long serialVersionUID = -5980768958647074106L;

	private final int beanId;
	private final long duration;
	private final IErrorCodeSet errors;
	private final R returnValue;

	public ExecutableBeanResponse(int beanId, long duration, R returnValue) {
		if (beanId < 0) {
			throw new IllegalArgumentException("beanId=" + beanId);
		}
		if (duration < 0) {
			throw new IllegalArgumentException("duration=" + duration);
		}
		if (returnValue == null) {
			throw new NullPointerException("returnValue");
		}
		this.beanId = beanId;
		this.duration = duration;
		this.returnValue = returnValue;
		this.errors = null;
	}

	public ExecutableBeanResponse(int beanId, long duration, IErrorCodeSet errors) {
		if (beanId < 0) {
			throw new IllegalArgumentException("beanId=" + beanId);
		}
		if (duration < 0) {
			throw new IllegalArgumentException("duration=" + duration);
		}
		if (errors == null) {
			throw new NullPointerException("errors");
		}
		if (errors.isEmpty()) {
			throw new IllegalArgumentException("errors is empty");
		}
		this.beanId = beanId;
		this.duration = duration;
		this.errors = errors;
		this.returnValue = null;
	}

	@Override
	public int getBeanId() {
		return beanId;
	}

	@Override
	public long getDuration() {
		return duration;
	}

	@Override
	public R getReturnValue() {
		return returnValue;
	}

	@Override
	public boolean hasReturnValue() {
		return returnValue != null;
	}

	@Override
	public IErrorCodeSet getErrors() {
		return errors;
	}

	@Override
	public boolean hasErrors() {
		return getErrors() != null;
	}

	@Override
	public String toString() {
		ToStringBuilder string = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		string.append("beanId", beanId);
		if (getReturnValue() != null) {
			string.append("returnValue", getReturnValue());
		}
		if (getErrors() != null) {
			string.append("errors", getErrors());
		}
		return string.toString();
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hash = new HashCodeBuilder();
		hash.append(getBeanId());
		hash.append(getReturnValue());
		hash.append(getErrors());
		return hash.toHashCode();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object object) {
		if (object == this) {
			return true;
		}
		if (object instanceof ExecutableBeanResponse) {
			ExecutableBeanResponse compare = (ExecutableBeanResponse) object;
			EqualsBuilder equals = new EqualsBuilder();
			equals.append(this.getBeanId(), compare.getBeanId());
			equals.append(this.getReturnValue(), compare.getReturnValue());
			equals.append(this.getErrors(), compare.getErrors());
			return equals.isEquals();
		}
		return false;
	}

}
