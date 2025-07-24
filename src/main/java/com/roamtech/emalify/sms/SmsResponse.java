package com.roamtech.emalify.sms;

public class SmsResponse {
    private String status;
    private String messageId;
    private String description;

    public String getStatus() {
        return status;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SmsResponse{status='" + status + "', messageId='" + messageId + "', description='" + description + "'}";
    }
}