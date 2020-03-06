package org.cqipc.edu.luosen.dao.impl;

import java.util.List;

import org.cqipc.edu.luosen.bean.Customer;
import org.cqipc.edu.luosen.dao.BaseDao;
import org.cqipc.edu.luosen.dao.CustomerDao;

public class CustomerDaoImpl extends BaseDao<CustomerDao> implements CustomerDao{
	public CustomerDaoImpl() {
		this.setMapper(CustomerDao.class);
	}
	@Override
	public List<Customer> findAll() {
		return this.getMapper().findAll();
	}

	@Override
	public List<Customer> findByBid(int b_id) {
		return this.getMapper().findByBid(b_id);
	}

	@Override
	public int create(Customer custmoer) {
		int count=this.getMapper().create(custmoer);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int modify(Customer custmoer) {
		int count=this.getMapper().modify(custmoer);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int remove(int c_id) {
		int count=this.getMapper().remove(c_id);
		this.sqlSession.commit();
		return count;
	}
	@Override
	public String findById(int c_id) {
		return this.getMapper().findById(c_id);
	}
	@Override
	public List<Customer> findByName(String c_name) {
		return this.getMapper().findByName(c_name);
	}

}
