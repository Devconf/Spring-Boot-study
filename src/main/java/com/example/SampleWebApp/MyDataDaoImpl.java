package com.example.SampleWebApp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
	private static final long serialVersionID =1L;
	
	private EntityManager entityManager;
	
	public MyDataDaoImpl() {
		super();
	}
	
	public MyDataDaoImpl(EntityManager manager) {
		this.entityManager=manager;
	}
	
	
	@Override
	public List<MyData> getAll() {
		Query query = this.entityManager.createQuery("from MyData");
		List<MyData> list = query.getResultList();
		this.entityManager.close();
		return list;
	}

}
