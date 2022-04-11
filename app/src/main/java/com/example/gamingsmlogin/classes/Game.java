
package com.example.gamingsmlogin.classes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("gameID")
    @Expose
    private Integer gameID;
    @SerializedName("gameName")
    @Expose
    private String gameName;
    @SerializedName("gameImage")
    @Expose
    private String gameImage;
    @SerializedName("gameTypeID")
    @Expose
    private Integer gameTypeID;
    @SerializedName("gameUrl")
    @Expose
    private String gameUrl;

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
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

    public Integer getGameTypeID() {
        return gameTypeID;
    }

    public void setGameTypeID(Integer gameTypeID) {
        this.gameTypeID = gameTypeID;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

}
