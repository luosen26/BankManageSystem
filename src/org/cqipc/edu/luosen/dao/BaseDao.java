package org.cqipc.edu.luosen.dao;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseDao <T>{
	private SqlSessionFactory sqlSessionFactory;
	public SqlSession sqlSession;
	private Class<T> mapper;
	public BaseDao() {
		getSSF();
		sqlSession=sqlSessionFactory.openSession(true);
	}
	public T getMapper() {
		return sqlSession.getMapper(mapper);
	}
	public void setMapper(Class<T> mapper) {
		this.mapper = mapper;
	}
	private void getSSF() {
		InputStream is;
		try {
			is = Resources.getResourceAsStream("MyBatis.xml");
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
