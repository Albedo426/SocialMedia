
package com.example.gamingsmlogin.classes;

import java.util.List;

import com.example.gamingsmlogin.classes.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsersReturn {

    @SerializedName("users")
    @Expose
    private List<User> users = null;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
