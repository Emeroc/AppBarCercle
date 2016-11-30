package fr.esstin.baresstin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esstin.baresstin.Extra;
import fr.esstin.baresstin.dao.ExtraDao;

public class ExtraDaoImpl implements ExtraDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Extra e) {
		em.persist(e);
		
	}

	@Override
	public void delete(long id) {
		Extra e = findById(id);
		if (e!=null)
		{
			e.setEnable(false);
			em.persist(e);
		}
		
	}

	@Override
	public List<Extra> list() {
		TypedQuery<Extra> q = em.createQuery("SELECT c FROM Extra c where c.enable=true order by c.name ASC", Extra.class);
		return q.getResultList();
	}

	@Override
	public Extra findById(long id) {
		TypedQuery<Extra> q = em.createQuery("SELECT c FROM Extra c WHERE c.id=:id", Extra.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

}
