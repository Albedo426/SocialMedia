
package com.example.gamingsmlogin.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("postId")
    @Expose
    private String postId;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("dataIsNull")
    @Expose
    private String dataIsNull;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("profilePhoto")
    @Expose
    private String profilePhoto;
    @SerializedName("likeCount")
    @Expose
    private String likeCount;
    @SerializedName("commentCount")
    @Expose
    private String commentCount;
    @SerializedName("didIslike")
    @Expose
    private String didIslike;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDataIsNull() {
        return dataIsNull;
    }

    public void setDataIsNull(String dataIsNull) {
        this.dataIsNull = dataIsNull;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getDidIslike() {
        return didIslike;
    }

    public void setDidIslike(String didIslike) {
        this.didIslike = didIslike;
    }

}
