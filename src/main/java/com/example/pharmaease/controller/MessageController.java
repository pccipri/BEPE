package com.example.pharmaease.controller;

import com.example.pharmaease.model.Message;
import com.example.pharmaease.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    Message getMessageById(@PathVariable Integer id) {

        return messageRepository.findById(id)
                .orElseThrow();
    }

    @PostMapping
    Message newMessage(@RequestBody Message newMessage) {
        return messageRepository.save(newMessage);
    }

    @PutMapping("/{id}")
    Message replaceMessage(@RequestBody Message newMessage, @PathVariable Integer id) {

        return messageRepository.findById(id)
                .map(message -> {
                    message.setMessage(newMessage.getMessage());
                    message.setUserId(newMessage.getUserId());
                    return messageRepository.save(message);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteMessage(@PathVariable Integer id) {
        messageRepository.deleteById(id);
    }

}