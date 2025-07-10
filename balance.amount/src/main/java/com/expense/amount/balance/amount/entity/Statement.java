//package com.expense.amount.balance.amount.entity;
//
//import com.fasterxml.jackson.annotation.JacksonAnnotation;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name="statement")
//@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class Statement {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @JsonProperty("account_Number")
//    private String accountNo;
//    @JsonProperty("accountHolder_name")
//    private String name;
//    private String customerID;
//    @JsonProperty("bank_name")
//    private String bank;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
//    private LocalDateTime statementDateTime;
//    @Embedded
//    private StatementPeriod statementPeriod;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "transaction_id")
//    private List<Transaction> transactions;
//
//}
