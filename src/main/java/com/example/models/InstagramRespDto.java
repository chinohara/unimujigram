package com.example.models;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class InstagramRespDto {
	
	@JsonProperty("pagination")
	private Pagination pagination;
	
	@JsonProperty("meta")
	private Meta meta;
	
	@JsonProperty("data")
	private List<InstagramInfo> data;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<InstagramInfo> getData() {
		return data;
	}

	public void setData(List<InstagramInfo> data) {
		this.data = data;
	}
}
