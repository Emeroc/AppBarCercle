package fr.esstin.baresstin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esstin.baresstin.Admin;
import fr.esstin.baresstin.dao.AdminDao;

public class AdminDaoImpl implements AdminDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void add(Admin a) {
		em.persist(a);
	}

	@Override
	public void update(Admin a) {
		em.persist(a);		
	}

	@Override
	public List<Admin> list() {
		TypedQuery<Admin> q = em.createQuery("SELECT a FROM Admin a", Admin.class);
		return q.getResultList();
	}

	@Override
	public Admin findById(long id) {
		TypedQuery<Admin> q = em.createQuery("SELECT a FROM Admin a WHERE a.id=:id", Admin.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Admin findByLogin(String login) {
		TypedQuery<Admin> q = em.createQuery("SELECT a FROM Admin a WHERE a.login=:login and a.enable=true", Admin.class);
		q.setParameter("login", login);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void delete(long id) {
		Admin a = findById(id);
		if(a!=null)
		{
			a.setEnable(false);
			em.persist(a);
		}
		
	}

	@Override
	public void reactivate(long id) {
		Admin a = findById(id);
		if(a!=null)
		{
			a.setEnable(true);
			em.persist(a);
		}
		
	}
	
	

}