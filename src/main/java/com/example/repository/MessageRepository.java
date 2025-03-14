package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("FROM message WHERE postedBy = :userId")
    List<Message> findAllByPostedBy(@Param("userId") Integer accId);

    @Query("FROM message WHERE messageId = :mId")
    Message findByMessageId(@Param("mId") Integer messageId);

    @Query("FROM message WHERE messageId = :mId")
    Message deleteByMessageId(@Param("mId") Integer messageId);
}
