# Xetova SMS Java SDK

A Java 11 compatible SDK for integrating with the Xetova SMS Gateway.

## Installation

Add the following to your Maven project:

```xml
<dependency>
    <groupId>com.xetova</groupId>
    <artifactId>xetova-sms-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

```java
XetovaSmsClient client = new XetovaSmsClient("your_api_key", "your_api_secret");
SmsRequest request = new SmsRequest("254712345678", "Hello from Xetova SDK!");
SmsResponse response = client.sendSms(request);
System.out.println(response);
```