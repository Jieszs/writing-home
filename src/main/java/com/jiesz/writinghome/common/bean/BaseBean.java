package com.jiesz.writinghome.common.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;


public class BaseBean implements Serializable {

	private static final long serialVersionUID = -5651501467149731391L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}