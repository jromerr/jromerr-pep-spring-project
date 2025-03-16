package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

//@Repository("messageRepo")
public interface MessageRepository extends JpaRepository<Message, Integer> {
    //@Query("FROM message WHERE postedBy = :userId")
    //List<Message> findAllByPostedBy(@Param("userId") Integer accId);
    Optional<List<Message>> findAllByPostedBy(Integer accId);

    //@Query("FROM message WHERE messageId = :mId")
    //Message findByMessageId(@Param("mId") Integer messageId);

    //@Modifying
    //@Query("DELETE FROM message m WHERE m.messageId = :mId")
    //int deleteByMessageIdAndGetCount(@Param("mId") Integer messageId);

    long countByMessageId(int messageId);

    //@Modifying
    //@Query("UPDATE message m SET m.messageText = :msg WHERE m.messageId = :mId")
    //int saveAndGetCount(@Param("msg") String newMessage, @Param("mId") int messageId);
}
