package com.Prince.forensicmart_admin.Bean;

import android.util.Log;

import java.util.ArrayList;

public class Courses_bean {

    public static Object UserBean;
    public ArrayList<Courses_bean.UserBean> data;
    public boolean status;

    public class UserBean {
        String image,name,status;

        public UserBean() {
        }

        public UserBean(String image, String name, String status) {
            this.image = image;
            this.name = name;
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
