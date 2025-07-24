//package com.roamtech.emalify.sms;
//
//import okhttp3.mockwebserver.MockResponse;
//import okhttp3.mockwebserver.MockWebServer;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class SmsClientTest {
//
//    private MockWebServer mockWebServer;
//    private SmsClient smsClient;
//
//    @BeforeEach
//    public void setup() throws IOException {
//        mockWebServer = new MockWebServer();
//        mockWebServer.start();
//        smsClient = new SmsClient("123", "test_api_key", "Adenzo") {
//            @Override
//            public SmsResponse sendSms(String recipient, String message, String clientSmsId) throws IOException {
//                // Override baseUrl for testing
//                this.baseUrl = mockWebServer.url("/api/services/sendbulk/").toString();
//                return super.sendSms(recipient, message, clientSmsId);
//            }
//        };
//    }
//
//    @AfterEach
//    public void teardown() throws IOException {
//        mockWebServer.shutdown();
//    }
//
//    @Test
//    public void testSendSmsSuccess() throws IOException {
//        String mockResponseBody = "{ \"status\": \"success\", \"messageId\": \"abc123\", \"description\": \"Message sent\" }";
//
//        mockWebServer.enqueue(new MockResponse()
//                .setResponseCode(200)
//                .setBody(mockResponseBody)
//                .addHeader("Content-Type", "application/json"));
//
//        SmsResponse response = smsClient.sendSms("0728334900", "Hello Test", "1234");
//
//        assertNotNull(response);
//        assertEquals("success", response.getStatus());
//        assertEquals("abc123", response.getMessageId());
//        assertEquals("Message sent", response.getDescription());
//    }
//}