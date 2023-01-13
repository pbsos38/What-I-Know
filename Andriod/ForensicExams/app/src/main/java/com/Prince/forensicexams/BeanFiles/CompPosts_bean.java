package com.Prince.forensicexams.BeanFiles;

import java.util.ArrayList;

public  class CompPosts_bean {
    public ArrayList<CompPosts_bean.UserBean> data;

    public static class UserBean {
        int id;
        String pic,url,uploadedat;

        public UserBean() {
        }

        public UserBean(int id, String pic, String url, String uploadedat) {
            this.id = id;
            this.pic = pic;
            this.url = url;
            this.uploadedat = uploadedat;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUploadedat() {
            return uploadedat;
        }

        public void setUploadedat(String uploadedat) {
            this.uploadedat = uploadedat;
        }
    }
}
