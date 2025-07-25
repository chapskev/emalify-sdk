package com.roamtech.emalify.sms;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

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
     * @param apiKey    Your Emalify API key
     * @param shortcode Your assigned shortcode
     */
    public SmsClient(String partnerId, String apiKey, String shortcode) {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = "https://api.v2.emalify.com/api/services/";
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
     * @throws IOException              if the API request fails or returns an unsuccessful response
     */
    public SmsResponse sendBulkSms(BulkSmsPayload payload) throws IOException {
        if (payload == null || payload.getSmslist().isEmpty()) {
            throw new IllegalArgumentException("Smslist cannot be null or empty");
        }
        for (SmsPayload sms : payload.getSmslist()) {
            if (sms.getMessage() == null || sms.getMobile() == null || sms.getApikey() == null || sms.getPartnerID() == null) {
                throw new IllegalArgumentException("Each SMS must have a non-null message, mobile, apikey, and partnerID");
            }
        }
        return execute("/sendsms", payload, SmsResponse.class);
    }

    /**
     * Sends a single SMS message.
     *
     * @param payload The SMS payload containing the message details
     * @return SmsResponse containing the API response
     * @throws IllegalArgumentException if the payload is null
     * @throws IOException              if the API request fails or returns an unsuccessful response
     */
    public SmsResponse sendSms(SmsPayload payload) throws IOException {
        if (payload.getMessage() == null) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        if (payload.getMobile() == null) {
            throw new IllegalArgumentException("Mobile cannot be null or empty");
        }
        if (payload.getApikey() == null) {
            throw new IllegalArgumentException("Api key cannot be null or empty");
        }
        if (payload.getPartnerID() == null) {
            throw new IllegalArgumentException("Partner ID key cannot be null or empty");
        }
        return execute("/sendsms", payload, SmsResponse.class);
    }

    /**
     * Executes an API request with the given payload.
     *
     * @param payload The request payload (either SmsPayload or BulkSmsPayload)
     * @return R containing the API response
     * @throws IOException if the API request fails or returns an unsuccessful response
     */
    private <T, R> R execute(String endpoint, T payload, Class<R> responseType) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl.concat(endpoint))
                .post(createJsonRequestBody(payload))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();

            String jsonResponse = responseBody.string();
            SmsResponse smsResponse = objectMapper.readValue(jsonResponse, SmsResponse.class);
            System.out.println("Response: " + smsResponse.getResponses().toString());
            smsResponse.setStatusCode(response.code());
            smsResponse.setMessage(response.message());
            smsResponse.setResponses(smsResponse.getResponses());
            return (R) smsResponse;

        } catch (IOException e) {
            throw new IOException("Response body is null");
        }

    }


    private RequestBody createJsonRequestBody(Object payload) throws IOException {
        return RequestBody.create(
                objectMapper.writeValueAsString(payload),
                MediaType.parse("application/json")
        );
    }

}