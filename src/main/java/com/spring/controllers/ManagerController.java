package com.spring.controllers;

import com.spring.beans.UserBean;
import com.spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    UserBean dbbean;

    @RequestMapping(value = "/editAccBtn",method = RequestMethod.GET)
    public ModelAndView changePage(@RequestParam(name = "id") Long id){
        Account account =  dbbean.getAccauntByID(id);
        ModelAndView mw = new ModelAndView("managerPages/editPages/EditAcc");
        mw.addObject("accaunt",account);
        return mw;
    }

    @RequestMapping(value = "/changeAcc",method = RequestMethod.GET)
    public String changeAcc(@RequestParam(name = "id") Long id,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "fullName") String fullname,
                            @RequestParam(name = "birthdate") Date birdate,
                            @RequestParam(name = "UIN") String ids,
                            @RequestParam(name = "blocked")String blocked){
        Account account =  dbbean.getAccauntByID(id);
        account.setName(name);
        account.setFirstName(fullname);
        account.setBirth_date(birdate);
        account.setUniqueIdentificationNumber(ids);
        account.setIs_blocked(blocked);
        dbbean.saveAnyThing(account);
        return "redirect:index";
    }


    @RequestMapping (value = "/creatAccPage",method = RequestMethod.GET)
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("managerPages/newAccaunt");
        List<Currencie> allCurrencie = dbbean.getAllCurrency();
        modelAndView.addObject("allCurrencie",allCurrencie);
        return modelAndView;
    }

    @RequestMapping(value = "addAccaunt",method = RequestMethod.GET)
    public String addAccaunt(@RequestParam(name = "name") String name,
                             @RequestParam(name = "firstName") String firstName,
                             @RequestParam(name = "birth_date") Date birth_date,
                             @RequestParam(name = "uniqueIdentificationNumber") String uIN,
                             @RequestParam(name = "currency_id") Long currency_id){
        Currencie currencie = dbbean.getCurrencyByID(currency_id);
        Account account = new Account(name,firstName,birth_date,uIN,currencie,0.0,null,"NO");
        Date date = new Date(System.currentTimeMillis());
        account.setCreated_date(date);
        dbbean.addAnything(account);
        return "redirect:index";
    }

    @RequestMapping(value = "transactionByManagerPage",method = RequestMethod.GET)
    public ModelAndView transactionPage(){
        ModelAndView modelAndView = new ModelAndView("managerPages/transactions");
        List<Account> accounts = dbbean.getAllAccount();
        modelAndView.addObject("accaunts",accounts);

        return modelAndView;
    }


    @RequestMapping(value = "sendMoney",method = RequestMethod.GET)
    public String sendMoney(
            @RequestParam(name = "accSender") Long senderId,
            @RequestParam(name = "accResiver") Long resiverId,
            @RequestParam(name = "amount") double amount,
            @RequestParam(name = "uniqueIdentificationNumber") String uIN){
        System.out.println(senderId+"  "+ resiverId+"  "+ amount +"  "+ uIN);

        Account accountSender = dbbean.getAccauntByID(senderId);
        Account accountResiver = dbbean.getAccauntByID(resiverId);


        List<Currency_exchange> arr = dbbean.getAllCurrencyExchange();

        Currencie senderCur = accountSender.getCurrency_id();
        Currencie resiverCur = accountResiver.getCurrency_id();

        double sendersMoney =accountSender.getAmount();
        double resiverMoney = accountResiver.getAmount();
        double bankCommision = amount*0.05;
        amount=amount-bankCommision;
        Currencie currencie1 = accountSender.getCurrency_id();
        Bank_total_balance bn = dbbean.getBankBalanceBYCurrency(currencie1);
        System.out.println(bn.toString());
        double balance = bn.getValue()+bankCommision;
        bn.setValue(balance);
        dbbean.saveAnyThing(bn);
        bankCommision=0;
        System.out.println(accountResiver.getUniqueIdentificationNumber());
        System.out.println("STRAT checking");
        //5$
        if (accountResiver.getUniqueIdentificationNumber().equals(uIN)) {
            System.out.println("STRAT 1");
            if (sendersMoney - amount > 0) {
                System.out.println("STRAT 2");
                //sender lose money
                sendersMoney -= amount;
                //check to  same currencie
                if (senderCur.getId().equals(resiverCur.getId())) {
                    System.out.println("STRAT 3");

                    resiverMoney += amount;
                    accountSender.setAmount(sendersMoney);
                    accountResiver.setAmount(resiverMoney);
                    dbbean.saveAnyThing(accountResiver);
                    dbbean.saveAnyThing(accountSender);
                    Bank_total_balance bank_total_balance =dbbean.getBankBalanceBYCurrency(senderCur);
                    bank_total_balance.setValue(bankCommision);
                    dbbean.saveAnyThing(bank_total_balance);
                    return "redirect:index";
                }
                //if currencie different
                else{
                    System.out.println("STRAT 3");
                    Long senderIDCur = senderCur.getId();
                    Long resivetIDCur =resiverCur.getId();
                    double tengeAmount =0;
                    //is it tenge?
                    if (!senderIDCur.equals(1L)){
                        System.out.println("STRAT 4");
                        for (int i=0;i<arr.size();i++){
                            if  (arr.get(i).getCurrency_id().getId().equals(senderIDCur)){
                                 double purchaseVal = arr.get(i).getPurchase_value();
                                 double saleVal = arr.get(i).getSale_value();
                                 bankCommision+=(((saleVal+purchaseVal)/2)-purchaseVal)*amount;
                                System.out.println("Bank " +bankCommision);
                                 amount=amount-(bankCommision/purchaseVal);
                                 tengeAmount=amount*purchaseVal;
                            }
                      }
                    }else {
                        System.out.println("STRAT 4");
                        tengeAmount=amount;
                    }

                    if (!resivetIDCur.equals(1L)){
                        System.out.println("STRAT 5");
                        for (int i=0;i<arr.size();i++){
                            if  (arr.get(i).getCurrency_id().getId().equals(resivetIDCur)){
                                double purchaseVal = arr.get(i).getPurchase_value();
                                double saleVal = arr.get(i).getSale_value();
                                System.out.println(purchaseVal+"  "+saleVal+ " "+resiverCur.getShortname());
                                bankCommision=((saleVal-((saleVal+purchaseVal)/2))*amount)+bankCommision;
                                double money = tengeAmount/saleVal;
                                resiverMoney +=money;
                            }
                        }
                    }else{
                        System.out.println("STRAT 5");
                        resiverMoney+=tengeAmount;
                    }
                 }
            }else { return "redirect:403";}
        }else {
            return "redirect:403";

        }

        accountResiver.setAmount(resiverMoney);
        accountSender.setAmount(sendersMoney);
        dbbean.saveAnyThing(accountResiver);
        dbbean.saveAnyThing(accountSender);
        Currencie currencie = dbbean.getCurrencyByID(1L);
        System.out.println(currencie.toString());
        Bank_total_balance bank_total_balance = dbbean.getBankBalanceBYCurrency(currencie);
        System.out.println(bank_total_balance.toString());
        double balanceBank = bank_total_balance.getValue()+bankCommision;
        bank_total_balance.setValue(balanceBank);
        bank_total_balance.setCurrency_id(currencie);
        dbbean.saveAnyThing(bank_total_balance);
        Date date = new Date(System.currentTimeMillis());
        Users user =dbbean.getUserData();
        Transaction_historie transaction_historie = new Transaction_historie(user,accountSender,accountResiver,1L,senderCur,amount,date);
        dbbean.addAnything(transaction_historie);

        return "redirect:index";
    }



    @RequestMapping (value = "credit",method = RequestMethod.GET)
    public String credit(
            @RequestParam(name = "accSender") Long id,
            @RequestParam(name = "amount") double amount,
            @RequestParam(name = "uniqueIdentificationNumber") String uIN){

        Account account = dbbean.getAccauntByID(id);
        double bankCom = 0;
        double sendedMoney = 0;
        if (uIN.equals(account.getUniqueIdentificationNumber())){
            System.out.println("OK 1 Check");
            if (account.getAmount()-amount>0){
                sendedMoney = amount;
                System.out.println("OK 2 Check");
                bankCom=amount*0.01;
                amount = account.getAmount()-amount;
                amount-=bankCom;
                System.out.println(amount);
            }
        }else {
            return "redirect:403";
        }
        addBankCommission(account.getCurrency_id(),bankCom);

        account.setAmount(amount);
        dbbean.saveAnyThing(account);

        Users manager = dbbean.getUserData();
        Date date = new Date(System.currentTimeMillis());
        Transaction_historie transaction_historie = new Transaction_historie(manager,account,account,4L,account.getCurrency_id(),sendedMoney,date);
        dbbean.addAnything(transaction_historie);

        return "redirect:index";
    }


    @RequestMapping (value = "debit",method = RequestMethod.GET)
    public String debit(
            @RequestParam(name = "accSender") Long id,
            @RequestParam(name = "amount") double amount,
            @RequestParam(name = "uniqueIdentificationNumber") String uIN){

        Account account = dbbean.getAccauntByID(id);
        double bankCom = 0;
        double sendedMoney =amount;
        if (uIN.equals(account.getUniqueIdentificationNumber())) {
            bankCom = amount*0.01;
            amount-=bankCom;
            amount+=account.getAmount();
        }else {
            return "redirect:403";
        }
        account.setAmount(amount);
        dbbean.saveAnyThing(account);
        addBankCommission(account.getCurrency_id(),bankCom);
        Users manager = dbbean.getUserData();
        Date date = new Date(System.currentTimeMillis());
        Transaction_historie transaction_historie = new Transaction_historie(manager,account,account,3L,account.getCurrency_id(),sendedMoney,date);
        dbbean.addAnything(transaction_historie);
        return "redirect:index";
    }

    @RequestMapping (value = "ExchangePageBtn",method = RequestMethod.GET)
    public ModelAndView ExchageDirectPage(){
        ModelAndView mw = new ModelAndView("managerPages/ExchangeMoney");
        List <Currency_exchange> arr = dbbean.getAllCurrencyExchange();
        mw.addObject("moneys",arr);
        return mw;
    }

    @RequestMapping (value = "exchangeMoneyDirect",method = RequestMethod.GET)
    public String ExchangeDirect(
            // type selectMoney amount
            @RequestParam(name = "type") String type,
            @RequestParam(name = "selectMoney") Long moneyId,
            @RequestParam(name = "amount") double amount){
        Users manager =dbbean.getUserData();
        Currency_exchange currency_exchange = dbbean.getCurrencyExchByID(moneyId);
        double moneyOut = 0;
        if (type.equals("buy")){
            moneyOut = amount/currency_exchange.getSale_value();
            System.out.println("You exchange and get " + moneyOut +" "+currency_exchange.getCurrency_id().getShortname());
        }
        else if (type.equals("sale")){
            moneyOut = amount*currency_exchange.getSale_value();
            System.out.println("You exchange and get " + moneyOut +" KZT ");
        }
        Date date = new Date(System.currentTimeMillis());
        Transaction_historie transaction_historie = new Transaction_historie(manager,null,null,2L,currency_exchange.getCurrency_id(),amount,date);
        dbbean.saveAnyThing(transaction_historie);

        return "redirect:index";
    }

    @RequestMapping (value = "clientsPage",method = RequestMethod.GET)
    public ModelAndView clientsPage(){
        ModelAndView modelAndView =new ModelAndView("managerPages/Clients");
        List <Account> accounts = dbbean.getAllAccount();
        modelAndView.addObject("accounts",accounts);
        return modelAndView;
    }


    @RequestMapping (value = "reciepts",method = RequestMethod.GET)
    public ModelAndView reciept(
            @RequestParam (name = "id") Long id
    ){
        Transaction_historie transaction_historie = dbbean.getHistoryByID(id);
        ModelAndView modelAndView = new ModelAndView("managerPages/RecieptPage");
        modelAndView.addObject("trHistory",transaction_historie);
        return modelAndView ;
    }

    @RequestMapping (value = "showallTransactionPage",method = RequestMethod.GET)
    public ModelAndView showallTransactionPage(@RequestParam (name = "id")Long id) {
        Account account = dbbean.getAccauntByID(id);
        List<Transaction_historie> transaction_histories = dbbean.getAllTransactions();
        ArrayList<Transaction_historie> arrayList = new ArrayList<Transaction_historie>();
        for (Transaction_historie arr : transaction_histories){
            if (arr.getReceiver_account_id().getId().equals(account.getId())){
                arrayList.add(arr);
            }else if (arr.getSender_account_id().getId().equals(account.getId())){
                arrayList.add(arr);
            }
        }
        ModelAndView modelAndView = new ModelAndView("managerPages/UserTransactions");
        modelAndView.addObject("arr",arrayList);
        return modelAndView;
    }























    public void addBankCommission(Currencie currencie , double amount){
        Bank_total_balance bank = dbbean.getBankBalanceBYCurrency(currencie);
        double money = bank.getValue();
        money += amount;
        bank.setValue(money);
        dbbean.saveAnyThing(bank);

    }


}
