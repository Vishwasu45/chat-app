package com.ai.chat.service.impl;

import com.ai.chat.contract.ChatRequest;
import com.ai.chat.contract.ChatResponse;
import com.ai.chat.contract.Choice;
import com.ai.chat.contract.Message;
import com.ai.chat.service.ChatService;
import com.ai.chat.service.ChatClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    @Override
    public List<String> chat(String query) {
        try {
            var response = chatClient.chat(buildChatRequest(query));
            ObjectMapper mapper = new ObjectMapper();
            var chatResponse = mapper.readValue(response, ChatResponse.class);
            return chatResponse.getChoices().stream()
                    .map(Choice::getMessage)
                    .map(Message::getContent)
                    .toList();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return List.of();
    }

    private ChatRequest buildChatRequest(String query) {
        var chatRequest = new ChatRequest();
        var message = new ChatRequest.Message();
        message.setContent(query);
        chatRequest.setMessages(List.of(message));
        return chatRequest;
    }
}
