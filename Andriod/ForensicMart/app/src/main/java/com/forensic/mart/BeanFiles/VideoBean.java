package com.forensic.mart.BeanFiles;

import java.util.ArrayList;

public class VideoBean {

    public static Object UserBean;
    public boolean status;
    public ArrayList<VideoBean.UserBean> data;

    public class UserBean {
        String name,link;
        int vid;

        public UserBean() {
        }

        public UserBean(String name, String link, int vid) {
            this.name = name;
            this.link = link;
            this.vid = vid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
        }
    }

}
