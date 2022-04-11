
package com.example.gamingsmlogin.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PlayerFeaturesForProfile {

    @SerializedName("opsiyon1")
    @Expose
    private String opsiyon1;
    @SerializedName("opsiyon2")
    @Expose
    private String opsiyon2;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("gUserName")
    @Expose
    private String gUserName;
    @SerializedName("gameName")
    @Expose
    private String gameName;
    @SerializedName("gameImage")
    @Expose
    private String gameImage;
    @SerializedName("source1")
    @Expose
    private String source1;
    @SerializedName("source2")
    @Expose
    private String source2;

    public String getOpsiyon1() {
        return opsiyon1;
    }

    public void setOpsiyon1(String opsiyon1) {
        this.opsiyon1 = opsiyon1;
    }

    public String getOpsiyon2() {
        return opsiyon2;
    }

    public void setOpsiyon2(String opsiyon2) {
        this.opsiyon2 = opsiyon2;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getgUserName() {
        return gUserName;
    }

    public void setgUserName(String gUserName) {
        this.gUserName = gUserName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameImage() {
        return gameImage;
    }

    public void setGameImage(String gameImage) {
        this.gameImage = gameImage;
    }

    public String getSource1() {
        return source1;
    }

    public void setSource1(String source1) {
        this.source1 = source1;
    }

    public String getSource2() {
        return source2;
    }

    public void setSource2(String source2) {
        this.source2 = source2;
    }

}
