
package com.example.gamingsmlogin.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("commentsId")
    @Expose
    private String commentsId;
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("profilePhoto")
    @Expose
    private String profilePhoto;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("isSubComment")
    @Expose
    private String isSubComment;
    @SerializedName("postId")
    @Expose
    private String postId;
    @SerializedName("subCommentCount")
    @Expose
    private String subCommentCount;

    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsSubComment() {
        return isSubComment;
    }

    public void setIsSubComment(String isSubComment) {
        this.isSubComment = isSubComment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getSubCommentCount() {
        return subCommentCount;
    }

    public void setSubCommentCount(String subCommentCount) {
        this.subCommentCount = subCommentCount;
    }

}
