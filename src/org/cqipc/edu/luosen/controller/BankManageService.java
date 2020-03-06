package org.cqipc.edu.luosen.controller;

import java.util.List;

import org.cqipc.edu.luosen.bean.Account;
import org.cqipc.edu.luosen.bean.Bank;
import org.cqipc.edu.luosen.bean.Customer;
import org.cqipc.edu.luosen.dao.AccountDao;
import org.cqipc.edu.luosen.dao.BankDao;
import org.cqipc.edu.luosen.dao.CustomerDao;

public interface BankManageService {
	public List<Bank> findBankAll(BankDao bankDao);
	public List<Bank> findBankByName(String b_name,BankDao bankDao);
	public boolean addBank(Bank bank,BankDao bankDao);
	public boolean modifyBank(Bank bank,BankDao bankDao);
	public boolean deleteBank(int b_id,BankDao bankDao,CustomerDao customerDao);
	public List<Customer> findCustomerAll(CustomerDao customerDao);
	public List<Customer> findCustomerByName(String c_name,CustomerDao customerDao);
	public boolean addCustomer(Customer customer,CustomerDao customerDao);
	public boolean modifyCustomer(Customer customer,CustomerDao customerDao);
	public boolean deleteCustomer(int c_id,CustomerDao customerDao);
	public List<Account> findAccountAll(AccountDao accountDao);
	public List<Account> findAccountByCid(int c_id,AccountDao accountDao);
	public Account findAccountById(int a_id,AccountDao accountDao);
	public boolean addAccount(Account account,AccountDao accountDao);
	public boolean modifyAccount(Account account,AccountDao accountDao);
	public boolean deleteAccount(int a_id,AccountDao accountDao);
	public boolean deposit(double money,int a_id,AccountDao accountDao);
	public boolean outMonry(double money,int a_id,AccountDao accountDao);
	public boolean remit(double depositBalance,double outMoneyBalance,double money,int outCid,int getCid,AccountDao accountDao);
}
