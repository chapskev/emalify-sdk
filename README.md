# Emalify SMS Java SDK

A Java 11 compatible SDK for integrating with the Emalify SMS Gateway. This SDK provides a simple interface to send single and bulk SMS messages.

## Installation

Add the following to your Maven project:
```xml
<dependency>
    <groupId>com.roamtech.emalify</groupId>
    <artifactId>emalify-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```
## Usage

### Initialize the Client
```java
// Create a new SMS client with your credentials
SmsClient client = new SmsClient(
"your_partner_id",
"your_api_key",
"your_shortcode"
);
```
### Send a Single SMS
```java
// Create an SMS payload
SmsPayload smsPayload = new SmsPayload(
"254712345678",  // recipient mobile number
"Hello from Emalify SDK!"  // message content
);

// Send the SMS
try {
SmsResponse response = client.sendSms(smsPayload);
System.out.println("Message sent successfully! Response: " + response);
} catch (IOException e) {
System.err.println("Failed to send SMS: " + e.getMessage());
}
```
### Send Bulk SMS
```java
// Create a bulk SMS payload
BulkSmsPayload bulkPayload = new BulkSmsPayload();

// Add multiple SMS messages
bulkPayload.addSms(new SmsPayload("5555555", "Message 1"));
bulkPayload.addSms(new SmsPayload("555555", "Message 2"));

// Send bulk SMS
try {
SmsResponse response = client.sendBulkSms(bulkPayload);
System.out.println("Bulk messages sent successfully! Response: " + response);
} catch (IOException e) {
System.err.println("Failed to send bulk SMS: " + e.getMessage());
}
```
## Environment Variables

You can also configure the SDK using environment variables:
```bash
EMALIFY_PARTNER_ID=your_partner_id
EMALIFY_API_KEY=your_api_key
EMALIFY_SHORTCODE=your_shortcode
EMALIFY_PASS_TYPE=plain  # optional, defaults to "plain"
```
When using environment variables, you can create SMS payloads without explicitly providing credentials:
```java
// Creates payload using environment variables
SmsPayload smsPayload = new SmsPayload("254712345678", "Hello!");
```
## Error Handling

The SDK throws the following exceptions:

- `IllegalArgumentException`: When required parameters are null or empty
- `IOException`: When the API request fails or returns an unsuccessful response

## Best Practices

1. **Reuse Client Instance**: Create a single `SmsClient` instance and reuse it
2. **Batch Processing**: Use `sendBulkSms` for sending multiple messages efficiently
3. **Error Handling**: Always implement proper error handling using try-catch blocks
4. **Cleanup**: The client handles connection cleanup automatically

## Sample Integration

Here's a complete example showing how to integrate the SDK:
```java
import com.roamtech.emalify.sms.BulkSmsPayload;
import com.roamtech.emalify.sms.SmsPayload;
import com.roamtech.emalify.sms.SmsResponse;

import java.io.IOException;

public class SmsDemo {
public static void main(String[] args) {
// Initialize client
SmsClient client = new SmsClient(
                                "your_partner_id",
                                "your_api_key",
                                "your_shortcode"
                                );

        // Send single SMS
        try {
            SmsPayload singleSms = new SmsPayload("254712345678", "Hello!");
            SmsResponse singleResponse = client.sendSms(singleSms);
            System.out.println("Single SMS sent: " + singleResponse);
        } catch (IOException e) {
            System.err.println("Single SMS failed: " + e.getMessage());
        }

        // Send bulk SMS
        try {
            BulkSmsPayload bulkSms = new BulkSmsPayload();
            bulkSms.addSms(new SmsPayload("", "Message 1"));
            bulkSms.addSms(new SmsPayload("", "Message 2"));
            
            SmsResponse bulkResponse = client.sendBulkSms(bulkSms);
            System.out.println("Bulk SMS sent: " + bulkResponse);
        } catch (IOException e) {
            System.err.println("Bulk SMS failed: " + e.getMessage());
        }
    }
}
```
## Support

For support and more information, please contact support@emalify.com

## License

This SDK is released under the MIT License. See the LICENSE file for details.
