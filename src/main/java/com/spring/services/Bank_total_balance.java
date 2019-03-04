package com.spring.services;

import javax.persistence.*;

@Entity
@Table(name = "bankBalance")
public class Bank_total_balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    @Column(name = "value")
    private double  value;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currencie currency_id;
    public Bank_total_balance() {
    }

    public Bank_total_balance(double value, Currencie currency_id) {
        this.value = value;
        this.currency_id = currency_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Currencie getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Currencie currency_id) {
        this.currency_id = currency_id;
    }

    @Override
    public String toString() {
        return "Bank_total_balance{" +
                "id=" + id +
                ", value=" + value +
                ", currency_id=" + currency_id +
                '}';
    }
}
