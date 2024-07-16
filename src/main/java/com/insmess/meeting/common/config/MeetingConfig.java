package com.insmess.meeting.common.config;

import io.openvidu.java.client.OpenVidu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeetingConfig {
    @Value("${insmess-meeting-server-url}")
    private String insmessMeetingServerUrl;

    @Value("${insmess-meeting-server-secret}")
    private String insmessMeetingServerSecret;
    @Bean
    public OpenVidu openVidu() {
        return new OpenVidu(insmessMeetingServerUrl, insmessMeetingServerSecret);
    }
}
