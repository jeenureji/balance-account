package com.expense.amount.balance.amount.service;


import com.expense.amount.balance.amount.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class OpenAiService {

    @Autowired
    TransactionRepository transactionRepository;

    public String returnOpenAiResponse(){
     try {
         File file = new File("src/main/resources/OpTransactionHistoryUX3_PDF08-06-2025 (1).pdf");
         PDDocument document = Loader.loadPDF(file);
         PDFTextStripper textStripper = new PDFTextStripper();
           String text = textStripper.getText(document);
          log.info("check the pdf text :{}", text);
         return text;
     }catch (Exception e){
         e.printStackTrace();
     }
     return "";
    }


    public String loadStatementData(){
        try {

            ObjectMapper objectMapper = new ObjectMapper();



            Runnable runnable = () ->{



            }


        }catch (Exception e){
            e.printStackTrace();
        }


    }










}
