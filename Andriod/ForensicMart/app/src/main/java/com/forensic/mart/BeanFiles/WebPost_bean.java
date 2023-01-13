package com.forensic.mart.BeanFiles;

import java.util.ArrayList;

public class WebPost_bean {
    public static Object UserBean;
    public boolean status;
    public ArrayList<WebPost_bean.UserBean> data;

    public class UserBean{
         private String title,url,file,blobfile,details,type,user_id,likes,created_at,updated_at,newfile,isNew;
            private int id;
        public UserBean() {
        }

        public UserBean(int id, String title, String url, String file, String blobfile, String details, String type, String user_id, String likes, String created_at, String updated_at, String newfile, String isNew) {
            this.id = id;
            this.title = title;
            this.url = url;
            this.file = file;
            this.blobfile = blobfile;
            this.details = details;
            this.type = type;
            this.user_id = user_id;
            this.likes = likes;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.newfile = newfile;
            this.isNew = isNew;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getBlobfile() {
            return blobfile;
        }

        public void setBlobfile(String blobfile) {
            this.blobfile = blobfile;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getLikes() {
            return likes;
        }

        public void setLikes(String likes) {
            this.likes = likes;
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

        public String getNewfile() {
            return newfile;
        }

        public void setNewfile(String newfile) {
            this.newfile = newfile;
        }

        public String getIsNew() {
            return isNew;
        }

        public void setIsNew(String isNew) {
            this.isNew = isNew;
        }
    }

}
