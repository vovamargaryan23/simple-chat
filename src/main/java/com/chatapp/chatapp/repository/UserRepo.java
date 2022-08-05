package com.chatapp.chatapp.repository;

import com.chatapp.chatapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepo extends JpaRepository<User, BigInteger> {
}
