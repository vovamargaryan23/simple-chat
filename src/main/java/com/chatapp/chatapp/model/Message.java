package com.chatapp.chatapp.model;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "messages")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id",columnDefinition = "BIGINT")
    @Getter
    private BigInteger message_id;

    @NonNull
    @Getter
    @Setter
    @Column(name = "message")
    private String message;

    @NonNull
    @Getter
    @Column(name = "user_id", columnDefinition = "BIGINT")
    private BigInteger user_id;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private User user;



}
