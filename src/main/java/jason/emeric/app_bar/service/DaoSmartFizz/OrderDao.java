package fr.esstin.baresstin.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import fr.esstin.baresstin.BalanceHistory;
import fr.esstin.baresstin.OrderItem;
import fr.esstin.baresstin.Order;
import fr.esstin.baresstin.Product;
import fr.esstin.baresstin.StatProductTranscient;

public interface OrderDao {

	public void save(Order o);
	
	public void save(OrderItem oi);
	
	public Order findById(long id);

	public List<Order> list();

	public List<Order> list(int offset, int limit);

	public List<Order> listPendingOrders();

	public List<OrderItem> listPendingOrderItems();

	public void delete(Order existingOrder);
	
	public OrderItem findItemById(long id);
	
	public boolean isOrderCompleted(long id);
	
	public void deleteOrderItem(OrderItem existingOrderItem);
	
	public List<Order> getCA(int year);
	
	public List<BalanceHistory> getContributorBonus(int year);
	
	public BigDecimal getCADay(Date date);

}
