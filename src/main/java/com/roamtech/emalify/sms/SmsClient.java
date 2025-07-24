package com.roamtech.emalify.sms;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class SmsClient {
    private final String apiKey;
    private final String apiSecret;
    private final OkHttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    public SmsClient(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.client = new OkHttpClient();
    }

    public SmsResponse sendSms(SmsRequest request) throws IOException {
        RequestBody body = RequestBody.create(
                mapper.writeValueAsString(request), MediaType.get("application/json"));
        Request httpRequest = new Request.Builder()
                .url("https://api.xetova.com/sms/send")
                .addHeader("X-API-KEY", apiKey)
                .addHeader("X-API-SECRET", apiSecret)
                .post(body)
                .build();
        try (Response response = client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response " + response);
            }
            return mapper.readValue(response.body().string(), SmsResponse.class);
        }
    }
}