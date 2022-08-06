package com.chatapp.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
public class JsonMessageData {
    @Getter
    @Setter
    public String message;
    @Getter
    @Setter
    public BigInteger user_id;
}
