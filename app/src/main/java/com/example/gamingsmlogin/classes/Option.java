
package com.example.gamingsmlogin.classes;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Option {

    @SerializedName("option1")
    @Expose
    private List<String> option1 = null;
    @SerializedName("option2")
    @Expose
    private List<String> option2 = null;

    public List<String> getOption1() {
        return option1;
    }

    public void setOption1(List<String> option1) {
        this.option1 = option1;
    }

    public List<String> getOption2() {
        return option2;
    }

    public void setOption2(List<String> option2) {
        this.option2 = option2;
    }

}
