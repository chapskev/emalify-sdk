package com.roamtech.emalify.sms;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SmsResponse {

    @JsonProperty("responses")
    private List<Response> responses;

    private int statusCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    private String message;

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public static class Response {

        @JsonProperty("response-code")
        private int responseCode;

        @JsonProperty("response-description")
        private String responseDescription;

        private String mobile;
        private String messageid;
        private String clientsmsid;
        private int networkid;

        // Getters and Setters
        public int getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        public String getResponseDescription() {
            return responseDescription;
        }

        public void setResponseDescription(String responseDescription) {
            this.responseDescription = responseDescription;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMessageid() {
            return messageid;
        }

        public void setMessageid(String messageid) {
            this.messageid = messageid;
        }

        public String getClientsmsid() {
            return clientsmsid;
        }

        public void setClientsmsid(String clientsmsid) {
            this.clientsmsid = clientsmsid;
        }

        public int getNetworkid() {
            return networkid;
        }

        public void setNetworkid(int networkid) {
            this.networkid = networkid;
        }
        @Override
        public String toString() {
            return String.format("Response{responseCode=%d, responseDescription='%s', mobile='%s', " +
                            "messageid='%s', clientsmsid='%s', networkid=%d}",
                    responseCode, responseDescription, mobile, messageid, clientsmsid, networkid);
        }

    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmsResponse{")
                .append("statusCode=").append(statusCode)
                .append(", message='").append(message);

        return sb.toString();
    }

}
