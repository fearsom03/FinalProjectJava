package com.spring.CampusService;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int stud_id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "date_of_birth")
    Date date;
    @ManyToOne
    @JoinColumn(name = "group_id")
    int group_id;
    @Column(name = "login")
    int login;
    @Column(name = "password")
    String password;

    public Student() {
    }

    public Student(int stud_id, String name, String surname, Date date, int group_id, int login, String password) {
        this.stud_id = stud_id;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.group_id = group_id;
        this.login = login;
        this.password = password;
    }

    public int getStud_id() {
        return stud_id;
    }

    public void setStud_id(int stud_id) {
        this.stud_id = stud_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getLogin() {
        return login;
    }

    public void setLogin(int login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stud_id=" + stud_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date=" + date +
                ", group_id=" + group_id +
                ", login=" + login +
                ", password='" + password + '\'' +
                '}';
    }
}
