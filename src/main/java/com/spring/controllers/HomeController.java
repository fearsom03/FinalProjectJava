package com.spring.controllers;

import com.spring.beans.UserBean;
import com.spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.rmi.CORBA.Util;
import java.sql.Date;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    UserBean dbbean;

    @RequestMapping(value = {"/enter","/"},method = RequestMethod.GET)
    public ModelAndView enter(){
        ModelAndView mw = new ModelAndView("login");
        return mw;
    }

        @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDeniedPage(){
        ModelAndView mw = new ModelAndView("403");
        return mw;
    }

    @RequestMapping(value = {"/main","/index"}, method = RequestMethod.GET)
    public ModelAndView main(){
        Users u=dbbean.getUserData();
        Set<Roles> r=u.getRoles();
        ModelAndView mv = new ModelAndView("managerPages/manager");
        List<Users> users =dbbean.getAllUsers();
        List<Users> admin = new ArrayList<Users>();
        List<Users> manager=new ArrayList<Users>();

        System.out.println(users.toString());
        for (Users user :users) {
           Set <Roles> roles = user.getRoles();
            for (Roles roo:roles) {
                if (roo.getId() == 1) {
                    admin.add(user);
                } else {
                    manager.add(user);
                }
            }
        }
        List<Transaction_historie> goodTransactions = getManagersTransactions();
        System.out.println(admin.toString()+"ADMINS");
        System.out.println(manager.toString()+"Managerss");
        mv.addObject("admins",admin);
        mv.addObject("managers",manager);

        for(Roles role: r) {
            if(role.getId()==1){
                mv.setViewName("adminPages/admin");
                mv.addObject("admin",u);
                return mv;
            }
        }
        System.err.println(goodTransactions+"     Transaction for manager");
        mv.addObject("goodTransactions",goodTransactions);
        mv.addObject("manager",u);
        return mv;
    }
    @RequestMapping(value = "/addManagerPage",method = RequestMethod.GET)
    public ModelAndView addManeger(){
        ModelAndView mv=new ModelAndView("adminPages/addManager");
        return mv;
    }


    @RequestMapping(value = "/allTransactionPage",method = RequestMethod.GET)
    public ModelAndView allTransactionPage(){
        ModelAndView mv=new ModelAndView("adminPages/allTransactions");
        List<Users> allusers = dbbean.getAllManagers();
//        List<Currency_exchange> currency_exchanges =dbbean.getAllCurrencyExchange();
        List<Account> accounts =dbbean.getAllAccount();
        List<Currencie> currencies = dbbean.getAllCurrency();
        List<Transaction_historie> transaction_histories = dbbean.getAllTransactions();
//        List<Operation> operations = dbbean.getAllOperation();
        mv.addObject("allTransactions",transaction_histories);
        mv.addObject("allAcc",accounts);
        mv.addObject("AllCur",currencies);
        mv.addObject("allManagers",allusers);
//        mv.addObject("oppper",operations);
        return mv;
    }


    @RequestMapping(value = "exchangePage" , method = RequestMethod.GET)
    public ModelAndView exchangePage(){
        ModelAndView mw = new ModelAndView("adminPages/exchangePage");
        List<Currency_exchange> currency_exchanges =dbbean.getAllCurrencyExchange();
        mw.addObject("curExc",currency_exchanges);
        List<Currencie> currencies = dbbean.getAllCurrency();
        mw.addObject("allCur",currencies);
        return mw;
    }

    @RequestMapping(value = "addManager" ,method = RequestMethod.GET)
    public String addUser(
            @RequestParam (name = "Login") String login,
            @RequestParam (name = "Pass") String pass,
            @RequestParam (name = "FullName") String fullName
    ){
        Set<Roles> arr = new HashSet<Roles>();
        Roles role = new Roles(2l,"ROLE_MANAGER");
        arr.add(role);
        Users user = new Users();
        user.setFullname(fullName);
        user.setLogin(login);
        user.setRoles(arr);
        try {
            pass = dbbean.encryptPassword(pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setPassword(pass);
        dbbean.addAnything(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/editButtom" , method = RequestMethod.GET)
    public ModelAndView editPage(@RequestParam(name = "id") Long id) {
        ModelAndView mw = new ModelAndView("adminPages/editPages/EditPage");
        Users user = (Users) dbbean.getUser(id);
        mw.addObject("user",user);
        return mw;
    }
    @RequestMapping(value = "/deleteButtom" , method = RequestMethod.GET)
    public String changing(@RequestParam(name = "id") Long id){
        dbbean.deleteUser(id);
        return "redirect:index";
    }

                           @RequestMapping(value = "/changeUser" , method =RequestMethod.GET)
    public String changing(@RequestParam(name = "login") String login,
                           @RequestParam(name = "pass")String pass,
                           @RequestParam(name = "fullname")String fullname,
                            @RequestParam(name = "id") Long id
    ){
            Users users =  dbbean.getUser(id);
        try {
            pass=dbbean.encryptPassword(pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
            users.setLogin(login);
            users.setPassword(pass);
            users.setFullname(fullname);
//            dbbean.saveUser(users);
        dbbean.saveAnyThing(users);
        return "redirect:index";

    }


    @RequestMapping(value = "changeExchange",method = RequestMethod.GET)
    public String changeExchange(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "id_cur") Long id_cur,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "shortname") String shortName,
            @RequestParam(name = "purchase_value") double buy,
            @RequestParam(name = "sale_value") double sale){
        Currency_exchange currency_exchange = new Currency_exchange();
        currency_exchange.setId(id);
        currency_exchange.setSale_value(sale);
        currency_exchange.setPurchase_value(buy);
        Currencie currencie = new Currencie(name,shortName);
        currencie.setId(id_cur);
        currency_exchange.setCurrency_id(currencie);
        Date date = new Date(System.currentTimeMillis());
        currency_exchange.setAssigned_time(date);
        dbbean.saveAnyThing(currency_exchange);
        return "redirect:index";
    }

    @RequestMapping(value = "/editExchangeButton",method = RequestMethod.GET)
    public ModelAndView changeValue(@RequestParam (name = "id") Long id){
        ModelAndView mw = new ModelAndView("adminPages/editPages/editExchange");
        Currency_exchange currency_exchange = dbbean.getCurrencyExchByID(id);
        mw.addObject("currency",currency_exchange);
        return mw;
    }

    @RequestMapping(value = "/BankBalancePage",method = RequestMethod.GET)
    public ModelAndView bankPage(){
        ModelAndView modelAndView = new ModelAndView("adminPages/bank_balance");
        List <Bank_total_balance> arr = dbbean.getAllMoney();
        modelAndView.addObject("bankMoney",arr);
        return modelAndView;
    }


    public List<Transaction_historie> getManagersTransactions(){
        Users manager = dbbean.getUserData();
        List<Transaction_historie> arr = dbbean.getAllTransactions();
        List<Transaction_historie> goodArr = new ArrayList<Transaction_historie>();
        for (Transaction_historie transactionHistorie: arr) {
            if (transactionHistorie.getManager_id().getId().equals(manager.getId())) {
                goodArr.add(transactionHistorie);
            }
        }
        System.err.println(goodArr+"KKKUUUKKKAAAAA");
        return goodArr;
    }
}
