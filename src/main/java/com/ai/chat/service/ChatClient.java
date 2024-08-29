package com.ai.chat.service;

import com.ai.chat.contract.ChatRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface ChatClient {

    @PostExchange
    String chat(@RequestBody ChatRequest chatRequest);
}
