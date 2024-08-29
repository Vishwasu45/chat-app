package com.ai.chat.web;

import com.ai.chat.contract.ChatMessage;
import com.ai.chat.contract.QueryRequest;
import com.ai.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private List<ChatMessage> chatHistory = new ArrayList<>();

    @GetMapping("/")
    public String getChat(Model model) {
        model.addAttribute("chatHistory", chatHistory);
        return "chat.html";
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message, Model model) {
        List<String> botResponse = chatService.chat(message);

        ChatMessage chatMessage = new ChatMessage(message, botResponse.get(0));
        chatHistory.add(chatMessage);

        model.addAttribute("chatHistory", chatHistory);
        return "chat.html";
    }


    @PostMapping
    public List<String> chat(@RequestBody QueryRequest request) {
        return chatService.chat(request.getQuery());
    }
}
