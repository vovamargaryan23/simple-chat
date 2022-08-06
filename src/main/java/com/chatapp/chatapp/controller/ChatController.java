package com.chatapp.chatapp.controller;

import com.chatapp.chatapp.model.JsonMessageData;
import com.chatapp.chatapp.model.Message;
import com.chatapp.chatapp.model.User;
import com.chatapp.chatapp.service.MessageService;
import com.chatapp.chatapp.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;

@Controller
@RequestMapping(value = "/chat", method = RequestMethod.GET)
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
        this.userService.addUser(new User(new BigInteger((String) Objects.requireNonNull(user.getAttribute("sub"))),
                user.getAttribute("name"),
                user.getAttribute("picture")));
        model.addAttribute("curr_user_id", this.userService.getbyId(new BigInteger((String) Objects.requireNonNull(user
                .getAttribute("sub")))).getUser_id());
        model.addAttribute("messages", messageService.getAll());
        model.addAttribute("message",new Message());
        return "chat";
    }



// Not Used
//    @PostMapping("/send")
//    public String sendMsg(@AuthenticationPrincipal OAuth2User user,@RequestParam("message") Optional<String> msg) {
//        if (msg.isPresent() && !msg.get().equals("")) {
//            this.messageService.addMessage(new Message(msg.get(), new BigInteger((String) Objects.requireNonNull(user
//                    .getAttribute("sub")))));
//        }
//        return "redirect:/chat";
//    }

    @MessageMapping("/msg")
    @SendTo("/topic/send")
    public Message sendMessage(@Payload final String json) throws IOException {
        JsonMessageData data = new ObjectMapper().readValue(json,JsonMessageData.class);
        return this.messageService.addMessage(new Message(data.getMessage(),data.user_id));
    }


}

//Add websockets