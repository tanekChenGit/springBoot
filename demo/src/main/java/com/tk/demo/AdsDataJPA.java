package com.tk.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adsdatajpa")
public class AdsDataJPA {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="TITLE")
	private String title;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="IMAGE_URL")
	private String imageUrl;
	@Column(name="ICON_URL")
	private String iconUrl;
	@Column(name="IMPRESSION_LINK")
	private String impressionLink;
	@Column(name="CLICK_URL")
	private String clickUrl;
	
	@Override
    public String toString() {
        return "AdsDataJPA{" +
                "id=" + id +
                ", name='" + title + '\'' +
                '}';
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getImpressionLink() {
		return impressionLink;
	}
	public void setImpressionLink(String impressionLink) {
		this.impressionLink = impressionLink;
	}
	public String getClickUrl() {
		return clickUrl;
	}
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	
}
