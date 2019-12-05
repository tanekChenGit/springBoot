package com.tk.obj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "url",
    "w",
    "h"
})
public class Img {

    @JsonProperty("url")
    private String url;
    @JsonProperty("w")
    private Integer w;
    @JsonProperty("h")
    private Integer h;

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("w")
    public Integer getW() {
        return w;
    }

    @JsonProperty("w")
    public void setW(Integer w) {
        this.w = w;
    }

    @JsonProperty("h")
    public Integer getH() {
        return h;
    }

    @JsonProperty("h")
    public void setH(Integer h) {
        this.h = h;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Img.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("w");
        sb.append('=');
        sb.append(((this.w == null)?"<null>":this.w));
        sb.append(',');
        sb.append("h");
        sb.append('=');
        sb.append(((this.h == null)?"<null>":this.h));
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
        result = ((result* 31)+((this.h == null)? 0 :this.h.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.w == null)? 0 :this.w.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Img) == false) {
            return false;
        }
        Img rhs = ((Img) other);
        return ((((this.h == rhs.h)||((this.h!= null)&&this.h.equals(rhs.h)))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.w == rhs.w)||((this.w!= null)&&this.w.equals(rhs.w))));
    }

}
