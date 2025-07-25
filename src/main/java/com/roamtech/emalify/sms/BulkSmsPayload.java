package com.roamtech.emalify.sms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kramer
 */
public class BulkSmsPayload {

    private int count;
    private List<SmsPayload> smslist = new ArrayList<>();


    public int getCount() {
        return smslist.size();
    }

    public List<SmsPayload> getSmslist() {
        return smslist;
    }

    public void setSmslist(List<SmsPayload> smslist) {
        this.smslist = smslist != null ? smslist : new ArrayList<>();
    }

    public void addSms(SmsPayload smsPayload) {
        if (smsPayload != null) {
            this.smslist.add(smsPayload);
        }
    }

    public boolean removeSms(SmsPayload smsPayload) {
        return this.smslist.remove(smsPayload);
    }

    public void clear() {
        this.smslist.clear();
    }
}
