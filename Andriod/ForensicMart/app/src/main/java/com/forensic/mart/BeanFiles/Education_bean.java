package com.forensic.mart.BeanFiles;

import java.util.ArrayList;

public class Education_bean {
    public static Object UserBean;
    public boolean status;
    public ArrayList<Education_bean.UserBean> data;
    public class UserBean{
    String id,user_id,school,fromDate,toDate,degree,details,created_at,updated_at;

    public UserBean(String id, String user_id, String school, String fromDate, String toDate, String degree, String details, String created_at, String updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.school = school;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.degree = degree;
        this.details = details;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
    }
}
