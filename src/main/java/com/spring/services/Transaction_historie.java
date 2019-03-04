package com.spring.services;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name ="transaction_histories")
public class Transaction_historie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Users  manager_id;
    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account sender_account_id;
    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiver_account_id;
    @Column(name = "operation_id")
    private Long operation_id;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currencie currency_id;
    @Column(name = "amount")
    private double amount;
    @Column(name = "operation_time")
    Date operation_time;

    public Transaction_historie() {
    }

    public Transaction_historie(Users manager_id, Account sender_account_id, Account receiver_account_id, Long operation_id, Currencie currency_id, double amount, Date operation_time) {
        this.manager_id = manager_id;
        this.sender_account_id = sender_account_id;
        this.receiver_account_id = receiver_account_id;
        this.operation_id = operation_id;
        this.currency_id = currency_id;
        this.amount = amount;
        this.operation_time = operation_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getManager_id() {
        return manager_id;
    }

    public void setManager_id(Users manager_id) {
        this.manager_id = manager_id;
    }

    public Account getSender_account_id() {
        return sender_account_id;
    }

    public void setSender_account_id(Account sender_account_id) {
        this.sender_account_id = sender_account_id;
    }

    public Account getReceiver_account_id() {
        return receiver_account_id;
    }

    public void setReceiver_account_id(Account receiver_account_id) {
        this.receiver_account_id = receiver_account_id;
    }

    public Long getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(Long operation_id) {
        this.operation_id = operation_id;
    }

    public Currencie getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Currencie currency_id) {
        this.currency_id = currency_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getOperation_time() {
        return operation_time;
    }

    public void setOperation_time(Date operation_time) {
        this.operation_time = operation_time;
    }

    @Override
    public String toString() {
        return "Transaction_historie{" +
                "id=" + id +
                ", manager_id=" + manager_id +
                ", sender_account_id=" + sender_account_id +
                ", receiver_account_id=" + receiver_account_id +
                ", operation_id=" + operation_id +
                ", currency_id=" + currency_id +
                ", amount=" + amount +
                ", operation_time=" + operation_time +
                '}';
    }
}
