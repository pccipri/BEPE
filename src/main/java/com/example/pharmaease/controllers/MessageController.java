package com.example.pharmaease.controllers;

import com.example.pharmaease.models.Message;
import com.example.pharmaease.repositories.MessageRepository;

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
@RequestMapping("/api/v1/messages")
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
                .map(message -> {
                    // Force lazy loading of the userType
                    message.getUser_id().getUsername(); // This triggers the database query
                    return message;
                })
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
                    message.setUser_id(newMessage.getUser_id());
                    return messageRepository.save(message);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    void deleteMessage(@PathVariable Integer id) {
        messageRepository.deleteById(id);
    }

}
