package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service("messageService")
public class MessageService {
    //@Autowired
    //@Qualifier("messageRepo")
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
        //return messageRepository.findByMessageId(messageId);
        return messageRepository.findById(messageId).get();
    }

    public int deleteMessage(int messageId){
        //return messageRepository.deleteByMessageIdAndGetCount((Integer)messageId);
        int rowsDeleted = (int) messageRepository.countByMessageId(messageId);
        messageRepository.deleteById(messageId);
        return rowsDeleted;
    }

    public Integer replaceMessage(int messageId, String newMessage){
        if(newMessage != "" && newMessage.length() <= 255 && messageRepository.findById(messageId).isPresent()){
            //return messageRepository.saveAndGetCount(newMessage, messageId);
            int rowsUpdated = (int) messageRepository.countByMessageId(messageId);
            Message updatedMessage = messageRepository.findById(messageId).get();
            updatedMessage.setMessageText(newMessage);
            messageRepository.save(updatedMessage);
            return rowsUpdated;
        }
        return null;
    }

    public List<Message> getAllMessagesFromAccount(int accountId){
        return messageRepository.findAllByPostedBy(accountId).get();
    }
}
