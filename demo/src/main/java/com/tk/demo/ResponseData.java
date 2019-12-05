package com.tk.demo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tk.obj.DataObj;
import com.tk.obj.Img;
import com.tk.obj.Link;

public class ResponseData {
	public static class AssetsEntry{
		@JsonProperty("type")
		private String type;
		@JsonProperty("data")
		private DataObj data;
		@JsonProperty("img")
		private Img img;
		@JsonProperty("link")
		private Link link;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public DataObj getData() {
			return data;
		}
		public void setData(DataObj data) {
			this.data = data;
		}
		public Img getImg() {
			return img;
		}
		public void setImg(Img img) {
			this.img = img;
		}
		public Link getLink() {
			return link;
		}
		public void setLink(Link link) {
			this.link = link;
		}
	}
	@JsonProperty("impressionLink")
	private String[] impressionLink;
	@JsonProperty("assets")
	private List<AssetsEntry> assets;
	
	public String[] getImpressionLink() {
		return impressionLink;
	}
	public void setImpressionLink(String[] impressionLink) {
		this.impressionLink = impressionLink;
	}
	public List<AssetsEntry> getAssets() {
		return assets;
	}
	public void setAssets(List<AssetsEntry> assets) {
		this.assets = assets;
	}
	
}
