package com.ai.chat.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "chat.client")
@Getter
@Setter
public class ChatClientConfigurationProperties {

    private String key;
}
