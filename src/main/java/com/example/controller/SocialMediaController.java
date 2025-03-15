package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account){
        Account accountExists = accountService.loginAccount(account);
        if(accountExists == null){
            Account addedAccount = accountService.addAccount(account);
            if(addedAccount != null){
                return ResponseEntity.status(200).body(addedAccount);
            }
            else{
                //return ResponseEntity.status(400).body("Registration unsuccessful")
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate username");
            }
        }
       //return ResponseEntity.status(409).body("Duplicate username");
       throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate username");
    }

    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account){
        Account accountLogin = accountService.loginAccount(account);
        if(accountLogin != null){
            return ResponseEntity.status(200).body(accountLogin);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login unsuccessful");
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> newMessage(@RequestBody Message message){
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage != null){
            return ResponseEntity.status(200).body(addedMessage);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message creation unsuccessful");
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> allMessages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(allMessages);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId){
        Message foundMessage = messageService.getMessageByMessageId(messageId);
        return ResponseEntity.status(200).body(foundMessage);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        int deletedCount = messageService.deleteMessage(messageId);
        if(deletedCount > 0) return ResponseEntity.status(200).body(deletedCount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int messageId, @RequestParam String messageText){
        int updatedCount = messageService.replaceMessage(messageId, messageText);
        if(updatedCount > 0) return ResponseEntity.status(200).body(updatedCount);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message update unsuccessful");
    }

    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUser(@PathVariable int accountId){
        List<Message> userMessages =messageService.getAllMessagesFromAccount(accountId);
        return ResponseEntity.status(200).body(userMessages);
    }
}
