package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository("messageRepo")
public interface MessageRepository extends JpaRepository<Message, Integer> {
    @Query("FROM message WHERE postedBy = :userId")
    List<Message> findAllByPostedBy(@Param("userId") Integer accId);

    @Query("FROM message WHERE messageId = :mId")
    Message findByMessageId(@Param("mId") Integer messageId);

    @Query("DELETE FROM message WHERE messageId = :mId")
    int deleteByMessageIdAndGetCount(@Param("mId") Integer messageId);

    @Query("UPDATE message SET messageText = :msg WHERE messageId = :mId")
    int saveAndGetCount(@Param("msg") String newMessage, @Param("mId") Integer messageId);
}
