package com.association.mansabar.model;

public class Advocate {
    private String name;
    private String enrollNo;
    private String picPath;
    private String mobile;
    private String chamberNo;

    public Advocate(String name, String enrollNo, String picPath, String mobile, String chamberNo) {
        this.name = name;
        this.enrollNo = enrollNo;
        this.picPath = picPath;
        this.mobile = mobile;
        this.chamberNo = chamberNo;
    }

    public Advocate() {
    }

    public String getName() {
        return name;
    }

    public String getEnrollNo() {
        return enrollNo;
    }

    public String getPicPath() {
        return picPath;
    }

    public String getMobile() {
        return mobile;
    }

    public String getChamberNo() {
        return chamberNo;
    }
}
