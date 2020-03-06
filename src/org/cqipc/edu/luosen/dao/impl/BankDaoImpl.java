package org.cqipc.edu.luosen.dao.impl;

import java.util.List;

import org.cqipc.edu.luosen.bean.Bank;
import org.cqipc.edu.luosen.dao.BankDao;
import org.cqipc.edu.luosen.dao.BaseDao;

public class BankDaoImpl extends BaseDao<BankDao> implements BankDao {
	public BankDaoImpl() {
		this.setMapper(BankDao.class);
	}
	@Override
	public List<Bank> findAll() {
		return this.getMapper().findAll();
	}

	@Override
	public int create(Bank bank) {
		int count=this.getMapper().create(bank);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int modify(Bank bank) {
		int count=this.getMapper().modify(bank);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int remove(int b_id) {
		int count=this.getMapper().remove(b_id);
		this.sqlSession.commit();
		return count;
	}
	@Override
	public String findById(int b_id) {
		return this.getMapper().findById(b_id);
	}
	@Override
	public List<Bank> findByName(String b_name) {
		return this.getMapper().findByName(b_name);
	}

}
