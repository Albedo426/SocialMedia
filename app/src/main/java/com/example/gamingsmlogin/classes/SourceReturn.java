
package com.example.gamingsmlogin.classes;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SourceReturn {

    @SerializedName("sources")
    @Expose
    private List<Source> sources = null;

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

}
