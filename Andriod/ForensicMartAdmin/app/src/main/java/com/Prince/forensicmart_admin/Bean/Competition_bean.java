package com.Prince.forensicmart_admin.Bean;

import java.util.ArrayList;

public class Competition_bean {
    public ArrayList<Competition_bean.UserBean> data;

    public static class UserBean {
        private String cid;
        private String name;
        private String isLive;
        private String qid;
        private String qno;
        private String ques;
        private String optA;
        private String optB;
        private String optC;
        private String optD;
        private String ans;

        
        public UserBean(String cid, String name, String isLive, String qid, String qno, String ques, String optA, String optB, String optC, String optD, String ans) {
            this.cid = cid;
            this.name = name;
            this.isLive = isLive;
            this.qid = qid;
            this.qno = qno;
            this.ques = ques;
            this.optA = optA;
            this.optB = optB;
            this.optC = optC;
            this.optD = optD;
            this.ans = ans;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIsLive() {
            return isLive;
        }

        public void setIsLive(String isLive) {
            this.isLive = isLive;
        }

        public String getQid() {
            return qid;
        }

        public void setQid(String qid) {
            this.qid = qid;
        }

        public String getQno() {
            return qno;
        }

        public void setQno(String qno) {
            this.qno = qno;
        }

        public String getQues() {
            return ques;
        }

        public void setQues(String ques) {
            this.ques = ques;
        }

        public String getOptA() {
            return optA;
        }

        public void setOptA(String optA) {
            this.optA = optA;
        }

        public String getOptB() {
            return optB;
        }

        public void setOptB(String optB) {
            this.optB = optB;
        }

        public String getOptC() {
            return optC;
        }

        public void setOptC(String optC) {
            this.optC = optC;
        }

        public String getOptD() {
            return optD;
        }

        public void setOptD(String optD) {
            this.optD = optD;
        }

        public String getAns() {
            return ans;
        }

        public void setAns(String ans) {
            this.ans = ans;
        }
    }


}
