
package com.example.gamingsmlogin.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerFeature {

    @SerializedName("playerFeaturesID")
    @Expose
    private String playerFeaturesID;
    @SerializedName("opsiyon1")
    @Expose
    private String opsiyon1;
    @SerializedName("opsiyon2")
    @Expose
    private String opsiyon2;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("gameID")
    @Expose
    private String gameID;
    @SerializedName("gUserName")
    @Expose
    private String gUserName;
    @SerializedName("source1")
    @Expose
    private String source1;
    @SerializedName("source2")
    @Expose
    private String source2;

    public String getPlayerFeaturesID() {
        return playerFeaturesID;
    }

    public void setPlayerFeaturesID(String playerFeaturesID) {
        this.playerFeaturesID = playerFeaturesID;
    }

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

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getgUserName() {
        return gUserName;
    }

    public void setgUserName(String gUserName) {
        this.gUserName = gUserName;
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
