package com.forensic.mart.BeanFiles;

import java.util.ArrayList;

public class Profile_data_bean {

    public ArrayList<Profile_data_bean> data;
    public String id,name, username, email, phone, email_otp, phone_otp, email_verify, phone_verify,
            password, country_id, blobpicture, designation, remember_token, created_at,
            updated_at, user_role,picture;

    public Profile_data_bean(String id,String name, String username, String email, String phone, String email_otp,
                             String phone_otp, String email_verify, String phone_verify, String password,
                             String country_id, String blobpicture, String designation, String remember_token,
                             String created_at, String updated_at, String user_role, String picture) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.email_otp = email_otp;
        this.phone_otp = phone_otp;
        this.email_verify = email_verify;
        this.phone_verify = phone_verify;
        this.password = password;
        this.country_id = country_id;
        this.picture = picture;
        this.blobpicture = blobpicture;
        this.designation = designation;
        this.remember_token = remember_token;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user_role = user_role;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail_otp() {
        return email_otp;
    }

    public void setEmail_otp(String email_otp) {
        this.email_otp = email_otp;
    }

    public String getPhone_otp() {
        return phone_otp;
    }

    public void setPhone_otp(String phone_otp) {
        this.phone_otp = phone_otp;
    }

    public String getEmail_verify() {
        return email_verify;
    }

    public void setEmail_verify(String email_verify) {
        this.email_verify = email_verify;
    }

    public String getPhone_verify() {
        return phone_verify;
    }

    public void setPhone_verify(String phone_verify) {
        this.phone_verify = phone_verify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getblobpicture() {
        return blobpicture;
    }

    public void setblobpicture(String blobpicture) {
        this.blobpicture = blobpicture;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
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

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
}
