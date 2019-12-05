package com.tk.obj;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "native"
})
public class JsonObj {

    @JsonProperty("native")
    private NativeObj _native;

    @JsonProperty("native")
    public NativeObj getNative() {
        return _native;
    }

    @JsonProperty("native")
    public void setNative(NativeObj _native) {
        this._native = _native;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(JsonObj.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("_native");
        sb.append('=');
        sb.append(((this._native == null)?"<null>":this._native));
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
        result = ((result* 31)+((this._native == null)? 0 :this._native.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof JsonObj) == false) {
            return false;
        }
        JsonObj rhs = ((JsonObj) other);
        return ((this._native == rhs._native)||((this._native!= null)&&this._native.equals(rhs._native)));
    }

}
