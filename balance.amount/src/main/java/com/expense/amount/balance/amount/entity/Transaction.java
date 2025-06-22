package com.expense.amount.balance.amount.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @JsonProperty("transaction_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private LocalDate date;
    @JsonProperty("transaction_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime time;
    @ManyToOne()
    @JoinColumn(name = "transaction_id")
    private String transactionId;
    private String description;
    @JsonProperty("type_of_transaction")
    private String type;
    @JsonProperty("transaction_to_name")
    private String name;
    private String bank;
    private String email;
    private  String debit;
    private String credit;
    private String amount;
    private String balance;

}
