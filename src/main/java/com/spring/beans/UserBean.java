package com.spring.beans;

import com.spring.services.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserBean{
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Users getUserData(){
        Users user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            UserDetails ud = (UserDetails) authentication.getPrincipal();
            user = getUser(ud.getUsername());
        }
        return user;
    }

    public void addAnything(Object object){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
    }
    public void saveAnyThing(Object onj){
        Session session =sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(onj);
        transaction.commit();
        session.close();
    }

    public List<Users> getAllUsers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);
        Root<Users> root = query.from(Users.class);
        List<Users> allUsers = session.createQuery(query).getResultList();
        session.close();
        return allUsers;
    }


    public List<Transaction_historie> getAllTransactions() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Transaction_historie> criteriaQuery = builder.createQuery(Transaction_historie.class);
        Root root = criteriaQuery.from(Transaction_historie.class);
        List<Transaction_historie> transaction_histories = session.createQuery(criteriaQuery).list();
        session.close();
        return transaction_histories;
    }

    public void deleteUser(Long id) {
        Users user = new Users();
        user.setId(id);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    public Currency_exchange getCurrencyExchByID(Long id) {

        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Currency_exchange> query = criteriaBuilder.createQuery(Currency_exchange.class);
        Root<Currency_exchange> root = query.from(Currency_exchange.class);
        Currency_exchange exchange = session.createQuery(query.where(criteriaBuilder.equal(root.get("id"), id))).getSingleResult();
        session.close();
        return exchange;
    }
    public Currencie getCurrencyByID(Long id){
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Currencie> query = criteriaBuilder.createQuery(Currencie.class);
        Root<Currencie> root = query.from(Currencie.class);
        Currencie currencie = session.createQuery(query.where(criteriaBuilder.equal(root.get("id"), id))).getSingleResult();
        session.close();
        return currencie;
    }
    public Transaction_historie getHistoryByID(Long id){
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Transaction_historie> query = criteriaBuilder.createQuery(Transaction_historie.class);
        Root<Transaction_historie> root = query.from(Transaction_historie.class);
        Transaction_historie transaction_historie = session.createQuery(query.where(criteriaBuilder.equal(root.get("id"), id))).getSingleResult();
        session.close();
        return transaction_historie;
    }
    public Users getUser(String login) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);
        Root<Users> root = query.from(Users.class);
        Users users = session.createQuery(query.where(criteriaBuilder.equal(root.get("login"), login))).getSingleResult();
        session.close();
        return users;
    }

    public String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }

    public  List<Currency_exchange> getAllCurrencyExchange(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Currency_exchange> criteriaQuery = builder.createQuery(Currency_exchange.class);
        Root root = criteriaQuery.from(Currency_exchange.class);
        List<Currency_exchange> currency_exchanges = session.createQuery(criteriaQuery).list();
        session.close();
        return currency_exchanges;
    }

    public List<Users> getAllManagers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Users> criteriaQuery = builder.createQuery(Users.class);
        Root root = criteriaQuery.from(Users.class);
        List<Users> users = session.createQuery(criteriaQuery).list();
        session.close();
        return users;
    }


    public  List<Account> getAllAccount(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
        Root root = criteriaQuery.from(Account.class);
        List<Account> accounts = session.createQuery(criteriaQuery).list();
        session.close();
        return accounts;
    }
    public  List<Currencie> getAllCurrency(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Currencie> criteriaQuery = builder.createQuery(Currencie.class);
        Root root = criteriaQuery.from(Currencie.class);
        List<Currencie> currencies = session.createQuery(criteriaQuery).list();
        session.close();
        return currencies;
    }
    public  List<Operation> getAllOperation(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Operation> criteriaQuery = builder.createQuery(Operation.class);
        Root root = criteriaQuery.from(Operation.class);
        List<Operation> operations = session.createQuery(criteriaQuery).list();
        session.close();
        return operations;
    }
    public List<Bank_total_balance> getAllMoney(){
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bank_total_balance> criteriaQuery = builder.createQuery(Bank_total_balance.class);
        Root root = criteriaQuery.from(Bank_total_balance.class);
        List<Bank_total_balance> operations = session.createQuery(criteriaQuery).list();
        session.close();
        return operations;
    }
    public Users getUser(Long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Users> query = criteriaBuilder.createQuery(Users.class);
        Root<Users> root = query.from(Users.class);
        Users users = session.createQuery(query.where(criteriaBuilder.equal(root.get("id"), id))).getSingleResult(); session.close();
        return users;
    }
    public Account getAccauntByID(Long id) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Account> query = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = query.from(Account.class);
        Account acc = session.createQuery(query.where(criteriaBuilder.equal(root.get("id"), id))).getSingleResult(); session.close();
        return acc;
    }


    public Bank_total_balance getBankBalanceBYCurrency(Currencie currencie) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder=session.getCriteriaBuilder();
        CriteriaQuery<Bank_total_balance> query = criteriaBuilder.createQuery(Bank_total_balance.class);
        Root<Bank_total_balance> root = query.from(Bank_total_balance.class);
        Bank_total_balance acc = session.createQuery(query.where(criteriaBuilder.equal(root.get("currency_id"), currencie))).getSingleResult(); session.close();
        return acc;
    }
}