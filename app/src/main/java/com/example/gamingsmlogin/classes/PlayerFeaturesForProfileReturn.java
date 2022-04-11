
package com.example.gamingsmlogin.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerFeaturesForProfileReturn {

    @SerializedName("playerFeaturesForProfile")
    @Expose
    private List<PlayerFeaturesForProfile> playerFeaturesForProfile = null;

    public List<PlayerFeaturesForProfile> getPlayerFeaturesForProfile() {
        return playerFeaturesForProfile;
    }

    public void setPlayerFeaturesForProfile(List<PlayerFeaturesForProfile> playerFeaturesForProfile) {
        this.playerFeaturesForProfile = playerFeaturesForProfile;
    }

}
