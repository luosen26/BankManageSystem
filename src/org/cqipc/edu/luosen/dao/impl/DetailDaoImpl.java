package org.cqipc.edu.luosen.dao.impl;

import java.util.List;

import org.cqipc.edu.luosen.bean.Detail;
import org.cqipc.edu.luosen.dao.BaseDao;
import org.cqipc.edu.luosen.dao.DetailDao;
public class DetailDaoImpl extends BaseDao<DetailDao> implements DetailDao {
	public DetailDaoImpl() {
		this.setMapper(DetailDao.class);
	}
	@Override
	public List<Detail> findAll() {
		return this.getMapper().findAll();
	}

	@Override
	public Detail findById(int d_id) {
		return this.getMapper().findById(d_id);
	}

	@Override
	public int create(Detail detail) {
		int count=this.getMapper().create(detail);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int modify(Detail detail) {
		int count=this.getMapper().modify(detail);
		this.sqlSession.commit();
		return count;
	}

	@Override
	public int remove(int d_id) {
		int count=this.getMapper().remove(d_id);
		this.sqlSession.commit();
		return count;
	}
	
}
