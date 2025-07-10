package com.expense.amount.balance.amount.controller;

import com.expense.amount.balance.amount.service.OpenAiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Module;
import org.aspectj.lang.annotation.RequiredTypes;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
@RestController
public class OpenAiController {



    private OpenAiService openAiService;
    private OpenAiChatModel chatModel;

    public OpenAiController(OpenAiChatModel openAiChatModel, OpenAiService openAiService){
        this.chatModel = openAiChatModel ;
        this.openAiService = openAiService;
    }

    @RequestMapping(value = "/runPrompt", method = RequestMethod.GET)
    public ResponseEntity<String> returnAiResponse() throws IOException {
       File file = new File("src/main/resources/docData");
        StringBuilder stringBuilder = new StringBuilder();
        String json3 = "{\n" +
                "    \"date_time\": \"09-05-2025 17:41:55\",\n" +
                "    \"transactionId\": \"X93594564\",\n" +
                "    \"transferred\": \"AR/026260247912 /DR/District/ICIC/districtdining\",\n" +
                "    \"debit\": 359.75,\n" + // OR use "credit" instead, depending on transaction+
                " \"credit\": null,\n"+
                "    \"balance\": 165824.68\n" +
                "}";
        String json1 = "you will be provide data it is a bank statement, I want you to go through the data and give the json object like";
        String json2 = "get fileds in Date and set it in date_time, get field in remarks and set in transferred, " +
                "get fileds in tran id-1 and set in transactionId, get filed in withdrawals and set in debit," +
                " get field in deposits and set in credit";
        String json4 = "give json data form this";
        stringBuilder.append(json1).append(json4);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String lines;
            while ((lines = bufferedReader.readLine())!= null){
                stringBuilder.append(lines);
            }
        }

       String response = chatModel.call(stringBuilder.toString());
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

    @RequestMapping(value = "/save/json", method = RequestMethod.GET)
    public ResponseEntity<String> saveJson() throws IOException {
        openAiService.saveTranstation();
        return ResponseEntity.status(HttpStatus.OK).body("completed");
    }


}
