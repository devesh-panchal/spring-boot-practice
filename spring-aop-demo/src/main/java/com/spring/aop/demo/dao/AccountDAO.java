package com.spring.aop.demo.dao;

import java.util.List;

import com.spring.aop.demo.Account;

public interface AccountDAO {
    void addAccount(Account account,boolean flag);
    boolean doWork();
    
    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
    
    // add a new method: findAccounts()

    List<Account> findAccounts();
    
    List<Account> findAccounts(boolean tripWire);

}
