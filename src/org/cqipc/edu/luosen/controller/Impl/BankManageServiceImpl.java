package org.cqipc.edu.luosen.controller.Impl;

import java.util.List;

import org.cqipc.edu.luosen.bean.Account;
import org.cqipc.edu.luosen.bean.Bank;
import org.cqipc.edu.luosen.bean.Customer;
import org.cqipc.edu.luosen.controller.BankManageService;
import org.cqipc.edu.luosen.dao.AccountDao;
import org.cqipc.edu.luosen.dao.BankDao;
import org.cqipc.edu.luosen.dao.CustomerDao;

public class BankManageServiceImpl implements BankManageService {

	@Override
	public List<Bank> findBankAll(BankDao bankDao) {
		return bankDao.findAll();
	}

	@Override
	public List<Customer> findCustomerAll(CustomerDao customerDao) {
		return customerDao.findAll();
	}

	@Override
	public List<Account> findAccountAll(AccountDao accountDao) {
		return accountDao.findAll();
	}


	@Override
	public Account findAccountById(int a_id, AccountDao accountDao) {
		return accountDao.findById(a_id);
	}

	@Override
	public boolean addBank(Bank bank, BankDao bankDao) {
		if (bankDao.create(bank)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifyBank(Bank bank, BankDao bankDao) {
		if (bankDao.modify(bank)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteBank(int b_id, BankDao bankDao,CustomerDao customerDao) {
		if (customerDao.findByBid(b_id).size()!=0) {
			return false;
		}else {
			if (bankDao.remove(b_id)>0) {
				return true;
			}else {
				return false;
			}
		}
	}

	@Override
	public List<Bank> findBankByName(String b_name, BankDao bankDao) {
		return bankDao.findByName(b_name);
	}

	@Override
	public boolean addCustomer(Customer customer, CustomerDao customerDao) {
		if (customerDao.create(customer)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Customer> findCustomerByName(String c_name, CustomerDao customerDao) {
		return customerDao.findByName(c_name);
	}

	@Override
	public boolean deleteCustomer(int c_id, CustomerDao customerDao) {
		if (customerDao.remove(c_id)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifyCustomer(Customer customer, CustomerDao customerDao) {
		if (customerDao.modify(customer)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean addAccount(Account account, AccountDao accountDao) {
		if (accountDao.create(account)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteAccount(int a_id, AccountDao accountDao) {
		if (accountDao.remove(a_id)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean modifyAccount(Account account, AccountDao accountDao) {
		if (accountDao.modify(account)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Account> findAccountByCid(int c_id, AccountDao accountDao) {
		return accountDao.findByCid(c_id);
	}

	@Override
	public boolean deposit(double money, int a_id, AccountDao accountDao) {
		if (accountDao.modifyBalanceByAid(money, a_id)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean outMonry(double money, int a_id, AccountDao accountDao) {
		if (accountDao.modifyBalanceByAid(money, a_id)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean remit(double depositBalance,double outMoneyBalance,double money, int outCid, int getCid, AccountDao accountDao) {
			if (outMonry((outMoneyBalance-money), outCid, accountDao)) {
				if (deposit((depositBalance+money), getCid, accountDao)) {
					return true;
				}else {
					deposit(money, outCid, accountDao);
					return false;
				}
			}else {
				return false;
			}
	}

}
