package com.roamtech.emalify.sms;

public class SmsPayload {
    private String partnerID;
    private String apikey;
    private String pass_type;
    private String clientsmsid;
    private String mobile;
    private String message;
    private String shortcode;


    public SmsPayload(String mobile, String message) {
        this.partnerID = System.getenv("EMALIFY_PARTNER_ID");
        this.apikey = System.getenv("EMALIFY_API_KEY");
        this.pass_type = System.getenv().getOrDefault("EMALIFY_PASS_TYPE", "plain");
        this.mobile = mobile;
        this.clientsmsid = generateClientSmsId();
        this.message = message;
        this.shortcode = System.getenv("EMALIFY_SHORTCODE");
    }

    public SmsPayload(String mobile, String message, String clientsmsid) {
        this.partnerID = System.getenv("EMALIFY_PARTNER_ID");
        this.apikey = System.getenv("EMALIFY_API_KEY");
        this.pass_type = System.getenv().getOrDefault("EMALIFY_PASS_TYPE", "plain");
        this.mobile = mobile;
        this.clientsmsid = clientsmsid;
        this.message = message;
        this.shortcode = System.getenv("EMALIFY_SHORTCODE");
    }


    public SmsPayload(String mobile,
                      String message,
                      String apikey,
                      String pass_type,
                      String partnerID,
                      String clientsmsid,
                      String shortcode) {
        this.partnerID = partnerID;
        this.apikey = apikey;
        this.pass_type = pass_type;
        this.clientsmsid = clientsmsid;
        this.mobile = mobile;
        this.message = message;
        this.shortcode = shortcode;
    }

    private String generateClientSmsId() {
        return java.util.UUID.randomUUID()
                .toString()
                .replaceAll("-", "");
    }

    // Getters and Setters
    public String getPartnerID() {
        return partnerID;
    }

    public void setPartnerID(String partnerID) {
        this.partnerID = partnerID;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getPass_type() {
        return pass_type;
    }

    public void setPass_type(String pass_type) {
        this.pass_type = pass_type;
    }

    public String getClientsmsid() {
        return clientsmsid;
    }

    public void setClientsmsid(String clientsmsid) {
        this.clientsmsid = clientsmsid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }
}
