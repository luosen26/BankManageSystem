package org.cqipc.edu.luosen.dao;

import java.util.List;

import org.cqipc.edu.luosen.bean.Detail;

public interface DetailDao {
	public List<Detail> findAll();
	public Detail findById(int d_id);
	public int create(Detail detail);
	public int modify(Detail detail);
	public int remove(int d_id);
}
