package com.spring.services;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name = "currency_exchange")
public class Currency_exchange {
//    id, currency_id,
// purchase_value,
// sale_value,
// assigned_time
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currencie currency_id;
    @Column(name = "purchase_value")
    private  double purchase_value;
    @Column(name = "sale_value")
    private  double sale_value;
    @Column(name = "assigned_time")
    private Date assigned_time;

    public Currency_exchange() {
    }

    public Currency_exchange(Currencie currency_id, Long purchase_value, Long sale_value, Date assigned_time) {
        this.currency_id = currency_id;
        this.purchase_value = purchase_value;
        this.sale_value = sale_value;
        this.assigned_time = assigned_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Currencie getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(Currencie currency_id) {
        this.currency_id = currency_id;
    }

    public double getPurchase_value() {
        return purchase_value;
    }

    public void setPurchase_value(double purchase_value) {
        this.purchase_value = purchase_value;
    }

    public double getSale_value() {
        return sale_value;
    }

    public void setSale_value(double sale_value) {
        this.sale_value = sale_value;
    }

    public Date getAssigned_time() {
        return assigned_time;
    }

    public void setAssigned_time(Date assigned_time) {
        this.assigned_time = assigned_time;
    }

    @Override
    public String toString() {
        return "Currency_exchange{" +
                "id=" + id +
                ", currency_id=" + currency_id +
                ", purchase_value=" + purchase_value +
                ", sale_value=" + sale_value +
                ", assigned_time=" + assigned_time +
                '}';
    }
}
