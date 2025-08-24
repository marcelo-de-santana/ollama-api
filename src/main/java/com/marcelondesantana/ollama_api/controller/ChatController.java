package com.marcelondesantana.ollama_api.controller;

import java.util.Map;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final OllamaChatModel chatModel;

    @GetMapping("/generativa")
    public Map<String, String> gerar(
            @RequestParam(value = "mensagem", defaultValue = "Me conte uma piada", required = false) String mensagem) {
        return Map.of("gerar", this.chatModel.call(mensagem));
    }

    @GetMapping("/generativa-stream")
    public Flux<ChatResponse> gerarStream(
            @RequestParam(value = "mensagem", defaultValue = "Me conte uma piada") String mensagem) {
        return this.chatModel.stream(new Prompt(new UserMessage(mensagem)));
    }
}
