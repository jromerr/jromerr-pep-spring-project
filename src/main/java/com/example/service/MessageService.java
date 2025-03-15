package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service("messageService")
public class MessageService {
    @Autowired
    @Qualifier("messageRepo")
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message){
        if(message.getMessageText() != "" && message.getMessageText().length() < 255 && messageRepository.findAllByPostedBy(message.getPostedBy()) != null){
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(int messageId){
        return messageRepository.findByMessageId(messageId);
    }

    public int deleteMessage(int messageId){
        return messageRepository.deleteByMessageIdAndGetCount(messageId);
    }

    public Integer replaceMessage(int messageId, String newMessage){
        if(newMessage != "" && newMessage.length() < 255 && messageRepository.findByMessageId(messageId) != null){
            return messageRepository.saveAndGetCount(newMessage, messageId);
        }
        return null;
    }

    public List<Message> getAllMessagesFromAccount(int accountId){
        return messageRepository.findAllByPostedBy(accountId);
    }
}
