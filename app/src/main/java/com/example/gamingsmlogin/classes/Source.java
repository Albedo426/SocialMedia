
package com.example.gamingsmlogin.classes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Source {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("source")
    @Expose
    private String source;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
