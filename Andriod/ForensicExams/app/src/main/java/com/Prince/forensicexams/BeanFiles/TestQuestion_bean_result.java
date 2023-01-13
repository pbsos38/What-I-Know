package com.Prince.forensicexams.BeanFiles;

import java.util.ArrayList;

public class TestQuestion_bean_result {
    public ArrayList<UserBean> data;

    public static class UserBean{
        int quesId,isActive;
        String quizId,question,optionA,optionB,optionC,optionD,answer,remarks,stmp;
        public UserBean() {
        }

        public UserBean(int quesId, int isActive, String quizId, String question,
                        String optionA, String optionB, String optionC, String optionD,
                        String answer, String remarks, String stmp) {
            this.quesId = quesId;
            this.isActive = isActive;
            this.quizId = quizId;
            this.question = question;
            this.optionA = optionA;
            this.optionB = optionB;
            this.optionC = optionC;
            this.optionD = optionD;
            this.answer = answer;
            this.remarks = remarks;
            this.stmp = stmp;
        }

        public int getQuesId() {
            return quesId;
        }

        public void setQuesId(int quesId) {
            this.quesId = quesId;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public String getQuizId() {
            return quizId;
        }

        public void setQuizId(String quizId) {
            this.quizId = quizId;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getOptionA() {
            return optionA;
        }

        public void setOptionA(String optionA) {
            this.optionA = optionA;
        }

        public String getOptionB() {
            return optionB;
        }

        public void setOptionB(String optionB) {
            this.optionB = optionB;
        }

        public String getOptionC() {
            return optionC;
        }

        public void setOptionC(String optionC) {
            this.optionC = optionC;
        }

        public String getOptionD() {
            return optionD;
        }

        public void setOptionD(String optionD) {
            this.optionD = optionD;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getStmp() {
            return stmp;
        }

        public void setStmp(String stmp) {
            this.stmp = stmp;
        }
    }

}
