package org.cqipc.edu.luosen.dao;

import java.util.List;

import org.cqipc.edu.luosen.bean.Customer;

public interface CustomerDao {
	public List<Customer> findAll();
	public List<Customer> findByBid(int b_id);
	public List<Customer> findByName(String c_name);
	public String findById(int c_id);
	public int create(Customer customer);
	public int modify(Customer customer);
	public int remove(int c_id);
}
