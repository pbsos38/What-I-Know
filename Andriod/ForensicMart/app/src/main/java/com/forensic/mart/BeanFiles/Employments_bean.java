package com.forensic.mart.BeanFiles;

import java.util.ArrayList;

public class Employments_bean {
    public static Object UserBean;
    public boolean status;
    public ArrayList<Employments_bean.UserBean> data;

    public class UserBean {
        String id, user_id, title, fromDate, toDate, details, created_at, updated_at;

        public UserBean(String id, String user_id, String title, String fromDate, String toDate, String details, String created_at, String updated_at) {
            this.id = id;
            this.user_id = user_id;
            this.title = title;
            this.fromDate = fromDate;
            this.toDate = toDate;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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