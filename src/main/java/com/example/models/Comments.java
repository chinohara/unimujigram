package com.example.models;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Comments {
	
	@JsonProperty("count")
	private BigDecimal count;
	
	// TODO data

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

}
