package fr.esstin.baresstin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esstin.baresstin.Menu;
import fr.esstin.baresstin.MenuPart;
import fr.esstin.baresstin.dao.MenuDao;

public class MenuDaoImpl implements MenuDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(Menu m) {
		m.setEnable(true);
		em.persist(m);		
	}

	@Override
	public Menu findById(long id) {
		TypedQuery<Menu> q = em.createQuery("SELECT m FROM Menu m WHERE m.id=:id", Menu.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Menu> list() {
		TypedQuery<Menu> q = em.createQuery("SELECT m FROM Menu m where m.enable=true", Menu.class);
		return q.getResultList();
	}

	@Override
	public void update(Menu m) {
		em.persist(m);
		
	}

	@Override
	public void delete(Menu m) {
		m.setEnable(false);
		em.persist(m);
		
	}

	@Override
	public MenuPart findMenuPartById(long id) {
		TypedQuery<MenuPart> q = em.createQuery("SELECT m FROM MenuPart m WHERE m.id=:id", MenuPart.class);
		q.setParameter("id", id);
		System.out.println("beforearf : "+id);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			System.out.println("arf");
			return null;
		}
	}
	
	@Override
	public List<MenuPart> listMenuPart(long id) {
		TypedQuery<MenuPart> q = em.createQuery("SELECT mp FROM MenuPart mp where mp.enable=true and mp.menu.id=:id", MenuPart.class);
		q.setParameter("id", id);
		return q.getResultList();
	}

}
