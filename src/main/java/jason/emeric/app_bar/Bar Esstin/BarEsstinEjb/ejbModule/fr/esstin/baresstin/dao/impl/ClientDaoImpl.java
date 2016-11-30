package fr.esstin.baresstin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esstin.baresstin.BalanceHistory;
import fr.esstin.baresstin.Client;
import fr.esstin.baresstin.Year;
import fr.esstin.baresstin.dao.ClientDao;

public class ClientDaoImpl implements ClientDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Client c) {
		em.persist(c);
	}

	@Override
	public Client findById(long id) {
		TypedQuery<Client> q = em.createQuery("SELECT c FROM Client c WHERE c.id=:id", Client.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Client> list() {
		TypedQuery<Client> q = em.createQuery("SELECT c FROM Client c where c.enable=true order by c.name ASC", Client.class);
		return q.getResultList();
	}

	@Override
	public void delete(Client existingClient) {
		existingClient.setEnable(false);
		em.persist(existingClient);
	}

	@Override
	public void addBalance(Client c, BalanceHistory b) {
		b.setClient(c);
		em.persist(b);
		c.setBalance(c.getBalance().add(b.getPrice()));
		em.persist(c);
	}

	@Override
	public List<BalanceHistory> listHistory(long clientId, int start) {
		TypedQuery<BalanceHistory> q = em.createQuery("SELECT b FROM BalanceHistory b where b.client.id=:id order by b.date DESC", BalanceHistory.class);
		q.setFirstResult(start);
		q.setMaxResults(20);
		q.setParameter("id", clientId);
		return q.getResultList();
	}

	@Override
	public Client findClientByLogin(String login) {
		TypedQuery<Client> q = em.createQuery("SELECT c FROM Client c WHERE c.login=:login and c.enable=true", Client.class);
		q.setParameter("login", login);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Year> listYear() {
		TypedQuery<Year> q = em.createQuery("SELECT y FROM Year y", Year.class);
		return q.getResultList();
	}

	@Override
	public Year findYearById(long id) {
		TypedQuery<Year> q = em.createQuery("SELECT c FROM Year c WHERE c.id=:id", Year.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}
}
