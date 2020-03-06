package org.cqipc.edu.luosen.dao;

import java.util.List;

import org.cqipc.edu.luosen.bean.Bank;

public interface BankDao {
	public List<Bank> findAll();
	public String findById(int b_id);
	public List<Bank> findByName(String b_name);
	public int create(Bank bank);
	public int modify(Bank bank);
	public int remove(int b_id);
}
