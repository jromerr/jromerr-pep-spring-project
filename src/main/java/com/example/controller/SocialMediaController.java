package com.example.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody Account account){
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Account account){
        return null;
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
