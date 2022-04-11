
package com.example.gamingsmlogin.classes;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamesReturn {

    @SerializedName("games")
    @Expose
    private List<Game> games = null;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

}
