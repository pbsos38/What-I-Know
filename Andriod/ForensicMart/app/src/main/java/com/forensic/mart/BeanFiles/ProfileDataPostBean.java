package com.forensic.mart.BeanFiles;

import java.util.ArrayList;

public class ProfileDataPostBean {
    public ArrayList<ProfileDataPostBean.UserBean> data;
    public class UserBean{
        String name,username,picture,country_id;

        public UserBean() {
        }

        public UserBean(String name, String username, String picture, String country_id) {
            this.name = name;
            this.username = username;
            this.picture = picture;
            this.country_id = country_id;
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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }
    }

}
