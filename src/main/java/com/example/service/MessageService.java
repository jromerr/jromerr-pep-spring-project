package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service("messageService")
public class MessageService {
    //@Autowired
    MessageRepository messageRepository;

    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message addMessage(Message message){
        if(message.getMessageText() != "" && message.getMessageText().length() <= 255 && accountRepository.findById(message.getPostedBy()).isPresent()){
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(int messageId){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            return message;
        }
        return null;
    }

    public int deleteMessage(int messageId){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(optionalMessage.isPresent()){
            int rowsDeleted = (int) messageRepository.countByMessageId(messageId);
            messageRepository.deleteById(messageId);
            return rowsDeleted;
        }
        return 0;
    }

    public int replaceMessage(int messageId, String newMessage){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if(newMessage != "" && newMessage.length() <= 255 && optionalMessage.isPresent()){
            int rowsUpdated = (int) messageRepository.countByMessageId(messageId);
            Message updatedMessage = optionalMessage.get();
            updatedMessage.setMessageText(newMessage);
            messageRepository.save(updatedMessage);
            return rowsUpdated;
        }
        return 0;
    }

    public List<Message> getAllMessagesFromAccount(int accountId){
        return messageRepository.findAllByPostedBy(accountId).get();
    }
}
