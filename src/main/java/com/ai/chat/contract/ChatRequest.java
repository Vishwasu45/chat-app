package com.ai.chat.contract;

import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {

    private String model = "gpt-4o";

    private final int max_tokens = 512;

    private boolean stream = false;

    private List<Message> messages;

    @Data
    public static class Message {
        private final String role = "user";
        private String content;
    }
}
