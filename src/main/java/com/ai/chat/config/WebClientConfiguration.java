package com.ai.chat.config;

import com.ai.chat.service.ChatClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

    private final ChatClientConfigurationProperties properties;

    @Bean
    public WebClient webClient() {
        WebClient.Builder builder = WebClient.builder();
        builder.baseUrl("https://api.aimlapi.com/chat/completions");
        builder.defaultHeader("Authorization", "Bearer " + properties.getKey());
        builder.filter(new ChatClientFilter());
        return builder.build();
    }

    @Bean
    public ChatClient googleClient(WebClient webClient) {
        return HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(WebClientAdapter.create(webClient))
                .build()
                .createClient(ChatClient.class);
    }
}
