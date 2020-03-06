package org.cqipc.edu.luosen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cqipc.edu.luosen.bean.Account;

public interface AccountDao {
	public List<Account> findAll();
	public List<Account> findByCid(int c_id);
	public Account findById(int a_id);
	public int create(Account account);
	public int modify(Account account);
	public int modifyBalanceByAid(@Param("a_balance")double money,@Param("a_id")int a_id);
	public int remove(int a_id);
}
