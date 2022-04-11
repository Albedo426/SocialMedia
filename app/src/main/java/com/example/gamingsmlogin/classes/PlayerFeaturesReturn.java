
package com.example.gamingsmlogin.classes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerFeaturesReturn {

    @SerializedName("playerFeatures")
    @Expose
    private List<PlayerFeature> playerFeatures = null;

    public List<PlayerFeature> getPlayerFeatures() {
        return playerFeatures;
    }

    public void setPlayerFeatures(List<PlayerFeature> playerFeatures) {
        this.playerFeatures = playerFeatures;
    }

}
