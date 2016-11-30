package fr.esstin.baresstin.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.esstin.baresstin.BalanceHistory;
import fr.esstin.baresstin.OrderItem;
import fr.esstin.baresstin.Order;
import fr.esstin.baresstin.StatProductTranscient;
import fr.esstin.baresstin.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Order o) {
		em.persist(o);
		em.flush();
	}

	@Override
	public Order findById(long id) {
		TypedQuery<Order> q = em.createQuery(
				"SELECT o FROM Order o WHERE o.id=:id", Order.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Order> list() {
		TypedQuery<Order> q = em.createQuery("SELECT o FROM Order o",
				Order.class);
		return q.getResultList();
	}

	@Override
	public List<Order> list(int offset, int limit) {
		TypedQuery<Order> q = em
				.createQuery(
						"SELECT o FROM Order o ORDER BY o.datePlaced DESC",
						Order.class);
		q.setFirstResult(offset).setMaxResults(limit);
		return q.getResultList();
	}

	@Override
	public List<Order> listPendingOrders() {
		TypedQuery<Order> q = em
				.createQuery(
						"SELECT o FROM Order o WHERE o.status='pending' OR o.status='partial' ORDER BY o.datePlaced ASC",
						Order.class);
		return q.getResultList();
	}

	@Override
	public List<OrderItem> listPendingOrderItems() {
		TypedQuery<OrderItem> q = em
				.createQuery(
						"SELECT oi FROM OrderItem oi WHERE oi.delivered=false ORDER BY oi.order.datePlaced ASC",
						OrderItem.class);
		return q.getResultList();
	}

	@Override
	public void delete(Order existingOrder) {
		em.remove(existingOrder);
	}

	@Override
	public boolean isOrderCompleted(long id) {
		Query q = em
				.createQuery("SELECT oi FROM OrderItem oi WHERE oi.delivered=false AND oi.order.id=:id");
		q.setParameter("id", id);
		return q.getResultList().size() == 0;
	}

	@Override
	public OrderItem findItemById(long id) {
		TypedQuery<OrderItem> q = em.createQuery(
				"SELECT oi FROM OrderItem oi WHERE oi.id=:id", OrderItem.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void deleteOrderItem(OrderItem existingOrderItem) {
		em.remove(existingOrderItem);
	}

	@Override
	public List<Order> getCA(int year) {
		TypedQuery<Order> q = em
				.createQuery(
						"SELECT o FROM Order o WHERE FUNC('YEAR', o.datePlaced)=:year ORDER BY o.datePlaced ASC",
						Order.class);
		q.setParameter("year", year);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public BigDecimal getCADay(Date date) {
		TypedQuery<BigDecimal> q = em
				.createQuery(
						"SELECT SUM(o.orderItemPrice) FROM OrderItem o WHERE o.cancelled=false and FUNC('DATE', o.order.datePlaced) = FUNC('DATE', :date)",
						BigDecimal.class);
		q.setParameter("date", date);
		try {
			return q.getSingleResult();
		} catch (NoResultException e) {
			return new BigDecimal(0);
		}
	}

	@Override
	public List<BalanceHistory> getContributorBonus(int year) {
		TypedQuery<BalanceHistory> q = em
				.createQuery(
						"SELECT o FROM BalanceHistory o WHERE FUNC('YEAR', o.date)=:year and o.status='2' ORDER BY o.date ASC",
						BalanceHistory.class);
		q.setParameter("year", year);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void save(OrderItem oi) {
		em.persist(oi);
	}

	// select p.name, count(p.ID) AS aaa from ORDERITEM o, PRODUCT p where p.ID
	// = o.productId CLIENTCLIENTgroup by p.ID having count(p.ID)
	

}
