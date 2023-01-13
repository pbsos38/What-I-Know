package com.example.searching.server;

import java.io.Serializable;
import java.util.ArrayList;

public class bean_fetch_Requests implements Serializable
{
    public static Object UserBean;
    public boolean status;


    public ArrayList<UserBean> data;
    public class UserBean {
        String rname,fname,gst,city;
        int status;


        public UserBean(String rname, String fname, String gst, int mobile, int status) {
            this.rname = rname;
            this.fname = fname;
            this.gst = gst;
            this.city =city;
            this.status = status;

        }

        public String getRname() {
            return rname;
        }

        public void setRname(String rname) {
            this.rname = rname;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getGst() {
            return gst;
        }

        public void setGst(String gst) {
            this.gst = gst;
        }

        public String getCity() { return city;}

        public void setCity(String city) {this.city = city;}

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
