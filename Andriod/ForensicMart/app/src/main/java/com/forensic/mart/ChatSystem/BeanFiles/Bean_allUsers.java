package com.forensic.mart.ChatSystem.BeanFiles;

public class Bean_allUsers {

    String Name;
    String Status;
      String newMsg,id;

    public Bean_allUsers() {
    }

    public Bean_allUsers(String Name, String id, String status,String newMsg) {
        this.Name = Name;
        this.Status = status;
      this.newMsg = newMsg;
      this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewMsg() {
        return newMsg;
    }

    public void setNewMsg(String newMsg) {
        this.newMsg = newMsg;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }
}
