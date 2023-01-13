package com.example.galaan_baatan.BeanFiles;

import java.util.List;

public class Messages_bean {
    private String sender;
    public String message;
    private String receiver;
    private String status;

    private List<Messages_bean> mChats;

    public Messages_bean() {    }

    public Messages_bean(String sender, String message, String receiver, String status) {
        this.sender = sender;
        this.message = message;
        this.receiver = receiver;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
