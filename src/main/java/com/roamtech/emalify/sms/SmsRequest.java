package com.roamtech.emalify.sms;

public class SmsRequest {
    private String to;
    private String message;

    public SmsRequest() {}

    public SmsRequest(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}