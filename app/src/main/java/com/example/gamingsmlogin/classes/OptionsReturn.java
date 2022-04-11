
package com.example.gamingsmlogin.classes;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OptionsReturn {

    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
