package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Integer> {

    Optional<List<Message>> findAllByPostedBy(Integer accId);

    long countByMessageId(int messageId);
}
