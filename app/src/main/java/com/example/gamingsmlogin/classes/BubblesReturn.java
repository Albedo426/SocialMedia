
package com.example.gamingsmlogin.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BubblesReturn {

    @SerializedName("bubbles")
    @Expose
    private List<Bubble> bubbles = null;

    public List<Bubble> getBubbles() {
        return bubbles;
    }

    public void setBubbles(List<Bubble> bubbles) {
        this.bubbles = bubbles;
    }

}
