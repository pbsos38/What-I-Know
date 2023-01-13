package com.Prince.paymentintegration.paytm;

import java.util.ArrayList;

public class bean_file_token_paytm {
    public ArrayList<bean_file_token_paytm.UserBean> body;


    public class UserBean {
        String head, body, responseTimestamp, version, signature, resultInfo, resultStatus, resultCode, resultMsg, txnToken, isPromoCodeValid, authenticated;

        public UserBean() {
        }

        public UserBean(String head, String body, String responseTimestamp, String version, String signature, String resultInfo,
                        String resultStatus, String resultCode, String resultMsg, String txnToken, String isPromoCodeValid,
                        String authenticated) {
            this.head = head;
            this.body = body;
            this.responseTimestamp = responseTimestamp;
            this.version = version;
            this.signature = signature;
            this.resultInfo = resultInfo;
            this.resultStatus = resultStatus;
            this.resultCode = resultCode;
            this.resultMsg = resultMsg;
            this.txnToken = txnToken;
            this.isPromoCodeValid = isPromoCodeValid;
            this.authenticated = authenticated;
        }

        public String getHead() {
            return head;
        }

        public void setHead(String head) {
            this.head = head;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getResponseTimestamp() {
            return responseTimestamp;
        }

        public void setResponseTimestamp(String responseTimestamp) {
            this.responseTimestamp = responseTimestamp;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getResultInfo() {
            return resultInfo;
        }

        public void setResultInfo(String resultInfo) {
            this.resultInfo = resultInfo;
        }

        public String getResultStatus() {
            return resultStatus;
        }

        public void setResultStatus(String resultStatus) {
            this.resultStatus = resultStatus;
        }

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }

        public String getTxnToken() {
            return txnToken;
        }

        public void setTxnToken(String txnToken) {
            this.txnToken = txnToken;
        }

        public String getIsPromoCodeValid() {
            return isPromoCodeValid;
        }

        public void setIsPromoCodeValid(String isPromoCodeValid) {
            this.isPromoCodeValid = isPromoCodeValid;
        }

        public String getAuthenticated() {
            return authenticated;
        }

        public void setAuthenticated(String authenticated) {
            this.authenticated = authenticated;
        }
    }
}
