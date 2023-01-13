package com.example.galaan_baatan.BeanFiles;

public class User_bean {

    private String id;
    private String Email;
    private String Image_Url;
    private String Name;
    private String Status;

    public User_bean() {
    }

    public User_bean(String id, String email, String image_Url,String Name,String Status) {
        this.id = id;
        this.Email = email;
        this.Image_Url = image_Url;
        this.Name = Name;
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getImage_Url() {
        return Image_Url;
    }

    public void setImage_Url(String image_Url) {
        Image_Url = image_Url;
    }
}
