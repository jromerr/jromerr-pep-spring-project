package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message){
        if(message.getMessageText() != "" && message.getMessageText().length() < 255 && messageRepository.getByPostedBy(message.getPostedBy()) != null){
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(int messageId){
        return messageRepository.getByMessageId(messageId);
    }

    public Message deleteMessage(int messageId){
        if(messageRepository.getByMessageId(messageId) != null){
            return messageRepository.deleteByMessageId(messageId);
        }
        return null;
    }

    public Message replaceMessage(Message message){
        if(message.getMessageText() != "" && message.getMessageText().length() < 255 && messageRepository.getByMessageId(message.getMessageId()) != null){
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessagesFromAccount(int accountId){
        return messageRepository.findAllbyPostedBy(accountId);
    }
}
