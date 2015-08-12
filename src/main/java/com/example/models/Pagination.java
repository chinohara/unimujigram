package com.example.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Pagination {
	
	@JsonProperty("next_max_tag_id")
	private String nextMaxTagId;
	
	@JsonProperty("deprecation_warning")
	private String deprecationWarning;
	
	@JsonProperty("next_max_id")
	private String nextMaxId;
	
	@JsonProperty("next_min_id")
	private String nextMinId;
	
	@JsonProperty("min_tag_id")
	private String minTagId;
	
	@JsonProperty("next_url")
	private String nextUrlString;

	public String getNextMaxTagId() {
		return nextMaxTagId;
	}

	public void setNextMaxTagId(String nextMaxTagId) {
		this.nextMaxTagId = nextMaxTagId;
	}

	public String getDeprecationWarning() {
		return deprecationWarning;
	}

	public void setDeprecationWarning(String deprecationWarning) {
		this.deprecationWarning = deprecationWarning;
	}

	public String getNextMaxId() {
		return nextMaxId;
	}

	public void setNextMaxId(String nextMaxId) {
		this.nextMaxId = nextMaxId;
	}

	public String getNextMinId() {
		return nextMinId;
	}

	public void setNextMinId(String nextMinId) {
		this.nextMinId = nextMinId;
	}

	public String getMinTagId() {
		return minTagId;
	}

	public void setMinTagId(String minTagId) {
		this.minTagId = minTagId;
	}

	public String getNextUrlString() {
		return nextUrlString;
	}

	public void setNextUrlString(String nextUrlString) {
		this.nextUrlString = nextUrlString;
	}

}
