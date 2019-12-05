package com.tk.obj;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "assets",
    "impressionLink"
})
public class NativeObj {

    @JsonProperty("assets")
    private List<Asset> assets = new ArrayList<Asset>();
    @JsonProperty("impressionLink")
    private List<String> impressionLink = new ArrayList<String>();

    @JsonProperty("assets")
    public List<Asset> getAssets() {
        return assets;
    }

    @JsonProperty("assets")
    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @JsonProperty("impressionLink")
    public List<String> getImpressionLink() {
        return impressionLink;
    }

    @JsonProperty("impressionLink")
    public void setImpressionLink(List<String> impressionLink) {
        this.impressionLink = impressionLink;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NativeObj.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("assets");
        sb.append('=');
        sb.append(((this.assets == null)?"<null>":this.assets));
        sb.append(',');
        sb.append("impressionLink");
        sb.append('=');
        sb.append(((this.impressionLink == null)?"<null>":this.impressionLink));
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
        result = ((result* 31)+((this.impressionLink == null)? 0 :this.impressionLink.hashCode()));
        result = ((result* 31)+((this.assets == null)? 0 :this.assets.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NativeObj) == false) {
            return false;
        }
        NativeObj rhs = ((NativeObj) other);
        return (((this.impressionLink == rhs.impressionLink)||((this.impressionLink!= null)&&this.impressionLink.equals(rhs.impressionLink)))&&((this.assets == rhs.assets)||((this.assets!= null)&&this.assets.equals(rhs.assets))));
    }

}
