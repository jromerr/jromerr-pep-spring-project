package com.example.controller;

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
    public ResponseEntity newMessage(@RequestBody Message message){
        return null;
    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages(){
        return null;
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity getMessageById(@PathVariable int messageId){
        return null;
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessageById(@PathVariable int messageId){
        return null;
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessage(@PathVariable int messageId){
        return null;
    }

    @GetMapping("accounts/{accountId}/messages")
    public ResponseEntity getAllMessagesByUser(@PathVariable int accountId){
        return null;
    }
}
