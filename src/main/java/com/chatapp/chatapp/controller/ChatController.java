package com.chatapp.chatapp.controller;

import com.chatapp.chatapp.model.Message;
import com.chatapp.chatapp.model.User;
import com.chatapp.chatapp.service.MessageService;
import com.chatapp.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;
    private UserService userService;

    @Autowired
    public ChatController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("")
    public String chat(@AuthenticationPrincipal OAuth2User user, Model model){
        //Create new user if doesn't exist
        this.userService.addUser(new User(new BigInteger((String) Objects.requireNonNull(user.getAttribute("sub"))),
                                            user.getAttribute("name"),
                                            user.getAttribute("picture")));

        //Get User info
        model.addAttribute("usr_name", this.userService.getbyId(new BigInteger((String) Objects.requireNonNull(user
                .getAttribute("sub")))).getUser_name());
        model.addAttribute("usr_pic",this.userService.getbyId(new BigInteger((String) Objects.requireNonNull(user
                .getAttribute("sub")))).getUser_pic());

        model.addAttribute("messages", messageService.getAll());
        model.addAttribute("message",new Message());
        return "chat";
    }



    @GetMapping("/send")
    public String sendMsg(@AuthenticationPrincipal OAuth2User user,@RequestParam("message") String msg){
        if (!msg.strip().equals("")) {
            this.messageService.addMessage(new Message(msg, new BigInteger((String) Objects.requireNonNull(user
                    .getAttribute("sub")))));
        }
        return "redirect:/chat";
    }
}


//TODO Join users to messages