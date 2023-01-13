package com.Prince.forensicexams.BeanFiles;

import java.util.ArrayList;

public class TestQuestion_bean {
    public ArrayList<UserBean> data;

    public static class UserBean {
    String quesId,question,optA,optB,optC,optD,answer,subject;

        public UserBean() {
        }

        public UserBean(String quesId, String question, String optA, String optB, String optC, String optD, String answer, String subject) {
            this.quesId = quesId;
            this.question = question;
            this.optA = optA;
            this.optB = optB;
            this.optC = optC;
            this.optD = optD;
            this.answer = answer;
            this.subject = subject;
        }

        public String getQuesId() {
            return quesId;
        }

        public void setQuesId(String quesId) {
            this.quesId = quesId;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
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

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }
}
