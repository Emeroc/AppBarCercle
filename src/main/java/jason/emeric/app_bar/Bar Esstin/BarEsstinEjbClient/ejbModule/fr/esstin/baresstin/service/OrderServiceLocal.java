package fr.esstin.baresstin.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import fr.esstin.baresstin.dto.CADto;
import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.dto.OrderItemDto;
import fr.esstin.baresstin.dto.ProductDto;

@Local
public interface OrderServiceLocal {
	
	public void add(OrderDto o, String admin);
	
	//public void update(OrderDto o);

	public List<OrderDto> list();
	
	public List<OrderDto> listHistory(int offset, int limit);

	public List<OrderDto> listPendingOrders();
	
	public List<OrderItemDto> listPendingOrderItems();
	
	public OrderDto findOrderById(long id);
	
	public void delete(long id);
	
	//public void addItemForOrder(long orderId, OrderItemDto item);
	
	public void deliverItem(OrderDto o, long itemId);
	
	public void cancelDeliverItem(OrderDto o, long itemId);
	
	public void removeItem(OrderDto o, long itemId, String admin);
	
	public List<CADto> getCA(int year);
	
	public BigDecimal getCADate(Date date);
	
	public List<CADto> getContributorBonus(int year);

}
