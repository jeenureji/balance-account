package com.expense.amount.balance.amount.service;


import com.expense.amount.balance.amount.entity.Transaction;
import com.expense.amount.balance.amount.entity.TransactionDao;
import com.expense.amount.balance.amount.repository.SaveTransaction;
import com.expense.amount.balance.amount.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class OpenAiService {

    @Autowired
    TransactionRepository transactionRepository;

    public String returnOpenAiResponse() {
        try {
            File file = new File("src/main/resources/OpTransactionHistoryUX3_PDF08-06-2025 (1).pdf");
            PDDocument document = Loader.loadPDF(file);
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(document);
            File file1 = new File("src/main/resources/docData");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1));
            bufferedWriter.write(text);
            bufferedWriter.close();
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

public void saveTranstation() throws IOException {

        File file = new File("src/main/resources/Transcation.json");
        ObjectMapper objectMapper = new ObjectMapper();
      Map<String, Object> transaction = objectMapper.readValue(file, Map.class);
    if(transaction != null  ){

        List<Map<String, Object>> list1 = (List<Map<String, Object>>) transaction.get("transactions");
            for(Map<String, Object> kk : list1){
                    String dateTime = kk.get("date") +" "+kk.get("time");
//                    System.out.println(dateTime);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
                  TransactionDao transactionDao = TransactionDao.builder().dateTime(localDateTime)
                          .transactionId((String) kk.get("transaction_id"))
                          .transferred((String) kk.get("transferred"))
                          .withdrawals((Double) kk.get("withdrawals"))
                          .deposits((Double) kk.get("deposits"))
                          .balance((Double) kk.get("balance")).build();
                  System.out.println(transactionDao);
                    Transaction transaction1 = new Transaction();
                    transaction1.setTransactionId(transactionDao.getTransactionId());
                    transaction1.setBalance(transactionDao.getBalance());
                    transaction1.setDeposits(transactionDao.getDeposits());
                    transaction1.setTransferred(transactionDao.getTransferred());
                    transaction1.setDateTime(transactionDao.getDateTime());
                    transaction1.setWithdrawals(transactionDao.getWithdrawals());
                   transactionRepository.save(transaction1);

            }


        }



}






}
