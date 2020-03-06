package org.cqipc.edu.luosen.dao.impl;

import java.util.List;

import org.cqipc.edu.luosen.bean.Account;
import org.cqipc.edu.luosen.dao.AccountDao;
import org.cqipc.edu.luosen.dao.BaseDao;

public class AccountDaoImpl extends BaseDao<AccountDao> implements AccountDao {
	public AccountDaoImpl() {
		this.setMapper(AccountDao.class);
	}
	@Override
	public List<Account> findAll() {
		return this.getMapper().findAll();
	}

	@Override
	public Account findById(int a_id) {
		return this.getMapper().findById(a_id);
	}

	@Override
	public int create(Account account) {
		int count=this.getMapper().create(account);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int modify(Account account) {
		int count=this.getMapper().modify(account);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int remove(int a_id) {
		int count=this.getMapper().remove(a_id);
		this.sqlSession.commit();
		return count;
	}
	@Override
	public List<Account> findByCid(int c_id) {
		return this.getMapper().findByCid(c_id);
	}
	
	@Override
	public int modifyBalanceByAid(double money, int a_id) {
		int count=this.getMapper().modifyBalanceByAid(money, a_id);
		this.sqlSession.commit();
		return count;
	}

}
