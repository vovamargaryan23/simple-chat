package com.chatapp.chatapp.service;

import com.chatapp.chatapp.model.User;
import com.chatapp.chatapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class UserService {
    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getbyId(BigInteger id){
        return this.userRepo.findById(id).orElseThrow(() -> null);
    }

    public Boolean userExists(BigInteger id){
        return this.userRepo.existsById(id);
    }

    public void addUser(User user){
        if(!userExists(user.getUser_id())){
            this.userRepo.save(user);
        }
    }
}
