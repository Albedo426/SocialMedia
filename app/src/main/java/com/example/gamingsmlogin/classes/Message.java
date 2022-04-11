
package com.example.gamingsmlogin.classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("messagesId")
    @Expose
    private String messagesId;
    @SerializedName("senderID")
    @Expose
    private String senderID;
    @SerializedName("receiverID")
    @Expose
    private String receiverID;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sendDate")
    @Expose
    private String sendDate;
    @SerializedName("vis")
    @Expose
    private String vis;
    @SerializedName("SenderImage")
    @Expose
    private String senderImage;
    @SerializedName("reciverImage")
    @Expose
    private String reciverImage;

    public String getMessagesId() {
        return messagesId;
    }

    public void setMessagesId(String messagesId) {
        this.messagesId = messagesId;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public String getReciverImage() {
        return reciverImage;
    }

    public void setReciverImage(String reciverImage) {
        this.reciverImage = reciverImage;
    }

}
