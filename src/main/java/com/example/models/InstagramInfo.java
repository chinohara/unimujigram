package com.example.models;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class InstagramInfo {
	
	@JsonProperty("attribution")
	private String attribution;
	
	@JsonProperty("tags")
	private List<String> tags;
	
	@JsonProperty("location")
	private Location location;
	
	@JsonProperty("comments")
	private Comments comments;
	
	@JsonProperty("filter")
	private String filter;
	
	@JsonProperty("created_time")
	private String createdTime;
	
	@JsonProperty("link")
	private String link;
	
	@JsonProperty("likes")
	private Likes likes;
	
	@JsonProperty("images")
	private Images images;
	
	@JsonProperty("users_in_photo")
	private List<String> userInPhoto;
	
	@JsonProperty("caption")
	private Caption caption;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("user")
	private User user;

	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Likes getLikes() {
		return likes;
	}

	public void setLikes(Likes likes) {
		this.likes = likes;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public List<String> getUserInPhoto() {
		return userInPhoto;
	}

	public void setUserInPhoto(List<String> userInPhoto) {
		this.userInPhoto = userInPhoto;
	}

	public Caption getCaption() {
		return caption;
	}

	public void setCaption(Caption caption) {
		this.caption = caption;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
