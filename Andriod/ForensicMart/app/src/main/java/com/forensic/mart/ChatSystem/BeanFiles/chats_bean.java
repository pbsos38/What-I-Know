package com.forensic.mart.ChatSystem.BeanFiles;

public class chats_bean {
    String message;
    String sender;
    String status;
    String push_id;

    public chats_bean() {
    }

    public chats_bean(String message, String sender, String status, String push_id) {
        this.message = message;
        this.sender = sender;
        this.status = status;
        this.push_id = push_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPush_id() {
        return push_id;
    }

    public void setPush_id(String push_id) {
        this.push_id = push_id;
    }
}
