package com.emalify.sdk;

import com.roamtech.emalify.sms.BulkSmsPayload;
import com.roamtech.emalify.sms.SmsPayload;
import com.roamtech.emalify.sms.SmsResponse;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * Client for interacting with the Emalify SMS Gateway API.
 * Provides functionality to send single and bulk SMS messages.
 */
public class SmsClient {
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;
    private final String baseUrl;
    private final String partnerId;
    private final String apiKey;
    private final String shortcode;

    /**
     * Creates a new SMS client with the specified credentials.
     *
     * @param partnerId Your Emalify partner ID
     * @param apiKey Your Emalify API key
     * @param shortcode Your assigned shortcode
     */
    public SmsClient(String partnerId, String apiKey, String shortcode) {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = "https://api.v2.emalify.com/api/services/sendbulk/";
        this.partnerId = partnerId;
        this.apiKey = apiKey;
        this.shortcode = shortcode;
    }

    /**
     * Sends a bulk SMS message to multiple recipients.
     *
     * @param payload The bulk SMS payload containing multiple messages
     * @return SmsResponse containing the API response
     * @throws IllegalArgumentException if the payload is null or contains no messages
     * @throws IOException if the API request fails or returns an unsuccessful response
     */
    public SmsResponse sendBulkSms(BulkSmsPayload payload) throws IOException {
        if (payload == null || payload.getSmslist().isEmpty()) {
            throw new IllegalArgumentException("Smslist cannot be null or empty");
        }

        Request request = new Request.Builder()
                .url(baseUrl)
                .post(createJsonRequestBody(payload))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to send SMS: " + response.code() + " - " + response.message());
            }

            return objectMapper.readValue(response.body().string(), SmsResponse.class);
        }
    }

    /**
     * Sends a single SMS message.
     *
     * @param smsPayload The SMS payload containing the message details
     * @return SmsResponse containing the API response
     * @throws IllegalArgumentException if the payload is null
     * @throws IOException if the API request fails or returns an unsuccessful response
     */
    public SmsResponse sendSms(SmsPayload smsPayload) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl)
                .post(createJsonRequestBody(smsPayload))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to send SMS: " + response.code() + " - " + response.message());
            }

            return objectMapper.readValue(response.body().string(), SmsResponse.class);
        }
    }

    /**
     * Executes an API request with the given payload.
     *
     * @param payload The request payload (either SmsPayload or BulkSmsPayload)
     * @return SmsResponse containing the API response
     * @throws IOException if the API request fails or returns an unsuccessful response
     */
    private <T> SmsResponse execute(T payload) throws IOException {
        // ... existing method code ...
    }

    private RequestBody createJsonRequestBody(Object payload) throws IOException {
        return RequestBody.create(
                objectMapper.writeValueAsString(payload),
                MediaType.parse("application/json")
        );
    }
}