package com.forensic.mart.BeanFiles;

import java.util.ArrayList;

public class Comments_bean {

    public static Object UserBean;
    public ArrayList<Comments_bean.UserBean> data;

    public static class UserBean {
        String id,post_id,user_id,message,created_at,updated_at;

        public UserBean() {
        }

        public UserBean(String id, String post_id, String user_id, String message, String created_at, String updated_at) {
            this.id = id;
            this.post_id = post_id;
            this.user_id = user_id;
            this.message = message;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPost_id() {
            return post_id;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
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
