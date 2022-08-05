package com.chatapp.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Getter
    @Id
    @Column(name="user_id",unique=true,columnDefinition = "BIGINT")
    private BigInteger user_id;

    @Getter
    @Column(name = "user_name")
    private String user_name;

    @Getter
    @Column(name = "user_pic")
    private String user_pic;
}
