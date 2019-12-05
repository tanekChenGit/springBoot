package com.tk.obj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "data",
    "img",
    "link"
})
public class Asset {

    @JsonProperty("type")
    private String type;
    @JsonProperty("data")
    private DataObj data;
    @JsonProperty("img")
    private Img img;
    @JsonProperty("link")
    private Link link;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("data")
    public DataObj getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(DataObj data) {
        this.data = data;
    }

    @JsonProperty("img")
    public Img getImg() {
        return img;
    }

    @JsonProperty("img")
    public void setImg(Img img) {
        this.img = img;
    }

    @JsonProperty("link")
    public Link getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Asset.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        sb.append("img");
        sb.append('=');
        sb.append(((this.img == null)?"<null>":this.img));
        sb.append(',');
        sb.append("link");
        sb.append('=');
        sb.append(((this.link == null)?"<null>":this.link));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.link == null)? 0 :this.link.hashCode()));
        result = ((result* 31)+((this.img == null)? 0 :this.img.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Asset) == false) {
            return false;
        }
        Asset rhs = ((Asset) other);
        return (((((this.link == rhs.link)||((this.link!= null)&&this.link.equals(rhs.link)))&&((this.img == rhs.img)||((this.img!= null)&&this.img.equals(rhs.img))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))));
    }

}
