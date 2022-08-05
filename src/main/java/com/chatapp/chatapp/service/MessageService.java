package com.chatapp.chatapp.service;

import com.chatapp.chatapp.model.Message;
import com.chatapp.chatapp.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private MessageRepo repo;

    @Autowired
    public void setRepo(MessageRepo repo) {
        this.repo = repo;
    }


    public List<Message> getAll(){
        return this.repo.findAll();
    }

    public Optional<Message> getById(BigInteger id){
        return this.repo.findById(id);
    }

    public void addMessage(Message message){
        this.repo.save(message);
    }
}
