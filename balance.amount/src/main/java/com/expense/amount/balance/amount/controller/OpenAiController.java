package com.expense.amount.balance.amount.controller;

import com.expense.amount.balance.amount.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OpenAiController {

    @Autowired
    OpenAiService openAiService;
    private OpenAiChatModel chatModel;

    public OpenAiController(OpenAiChatModel openAiChatModel){
        this.chatModel = openAiChatModel ;
    }

    @RequestMapping(value = "/runPrompt/{message}", method = RequestMethod.GET)
    public ResponseEntity<String> returnAiResponse(@PathVariable String message){
        log.info("check the prompt message :{}", message);
       String response = chatModel.call(message);
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @RequestMapping(value = "/getPdfText", method = RequestMethod.GET)
    public ResponseEntity<String> returnPdfText() {
        try {
            String text = openAiService.returnOpenAiResponse();
            return ResponseEntity.status(HttpStatus.OK).body(text);
        } catch (Exception e) {
            log.info("check the exception : {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
