package com.example.SampleWebApp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;

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
		List<MyData> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		query.select(root);
		list = (List<MyData>) entityManager.createQuery(query).getResultList();
		this.entityManager.close();
		return list;
	}

	@Override
	public MyData findById(long id) {
		return (MyData)this.entityManager.createQuery("from MyData where id = "+id).getSingleResult();
	}

	@Override
	public List<MyData> findByName(String name) {
		return (List<MyData>)this.entityManager.createQuery("from MyData where name = '"+ name+"'").getResultList();
	}

	@Override
	public List<MyData> find(String fstr) {
		List<MyData> list =null;
		Long fid =0L;
		try {
			fid= Long.parseLong(fstr);
		}catch(NumberFormatException e) {
			//e.printStackTrace();
		}
		Query query = entityManager.createNamedQuery("findWithIdAndNameAndMail").setParameter("fid", fid)
				.setParameter("fname", "%" + fstr + "%")
				.setParameter("fmail", fstr + "@%");
		list =query.getResultList();
		return list;
	}

}
