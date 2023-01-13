package com.Prince.forensicmart_admin.Bean;

import java.util.ArrayList;

public class Posts_beans {

    public static Object UserBean;
    public boolean status;
    public ArrayList<Posts_beans.UserBean> data;

    public static class UserBean {
        String image, postname, postlink,website;

        public UserBean() {
        }

        public UserBean(String image, String postname, String postlink,String website) {
            this.image = image;
            this.postname = postname;
            this.postlink = postlink;
            this.website = website;

        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPostname() {
            return postname;
        }

        public void setPostname(String postname) {
            this.postname = postname;
        }

        public String getPostlink() {
            return postlink;
        }

        public void setPostlink(String postlink) {
            this.postlink = postlink;
        }
    }
}
