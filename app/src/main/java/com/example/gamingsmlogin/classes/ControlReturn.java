
package com.example.gamingsmlogin.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ControlReturn {

    @SerializedName("control")
    @Expose
    private String control;

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

}
