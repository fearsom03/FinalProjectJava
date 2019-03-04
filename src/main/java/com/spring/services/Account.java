package com.spring.services;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "firstName", length = 100)
    private String firstName;

    @Column(name = "birth_date")
    private Date birth_date ;

    @Column(name = "UIN")
    private String uniqueIdentificationNumber;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currencie currency_id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "created_date")
    private Date created_date ;

    @Column(name = "is_blocked" , length = 30)
    private String is_blocked;

    public Account() {
    }

    public Account(String name, String firstName, Date birth_date, String uniqueIdentificationNumber, Currencie currency_id, double amount, Date created_date, String is_blocked) {
        this.name = name;
        this.firstName = firstName;
        this.birth_date = birth_date;
        this.uniqueIdentificationNumber = uniqueIdentificationNumber;
        this.currency_id = currency_id;
        this.amount = amount;
        this.created_date = created_date;
        this.is_blocked = is_blocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getUniqueIdentificationNumber() {
        return uniqueIdentificationNumber;
    }

    public void setUniqueIdentificationNumber(String uniqueIdentificationNumber) {
        this.uniqueIdentificationNumber = uniqueIdentificationNumber;
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

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(String is_blocked) {
        this.is_blocked = is_blocked;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birth_date=" + birth_date +
                ", uniqueIdentificationNumber=" + uniqueIdentificationNumber +
                ", currency_id=" + currency_id +
                ", amount=" + amount +
                ", created_date=" + created_date +
                ", is_blocked='" + is_blocked + '\'' +
                '}';
    }
}
