package com.example.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Images {
	
	@JsonProperty("low_resolution")
	private Image lowResolution;
	
	@JsonProperty("standard_resolution")
	private Image standardResolution;
	
	@JsonProperty("thumbnail")
	private Image thumbnail;

	public Image getLowResolution() {
		return lowResolution;
	}

	public void setLowResolution(Image lowResolution) {
		this.lowResolution = lowResolution;
	}

	public Image getStandardResolution() {
		return standardResolution;
	}

	public void setStandardResolution(Image standardResolution) {
		this.standardResolution = standardResolution;
	}

	public Image getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
}
