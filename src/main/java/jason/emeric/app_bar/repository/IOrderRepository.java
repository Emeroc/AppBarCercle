package jason.emeric.app_bar.repository;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



public interface IOrderRepository {

	public void save(OrderEntity o);
	
	public void save(OrderItemEntity oi);
	
	public OrderEntity findById(long id);

	public List<OrderEntity> list();

	public List<OrderEntity> list(int offset, int limit);

	public List<OrderEntity> listPendingOrders();

	public List<OrderItemEntity> listPendingOrderItems();

	public void delete(OrderEntity existingOrder);
	
	public OrderItemEntity findItemById(long id);
	
	public boolean isOrderCompleted(long id);
	
	public void deleteOrderItem(OrderItemEntity existingOrderItem);
	
	public List<OrderEntity> getCA(int year);
	
	public List<BalanceHistory> getContributorBonus(int year);
	
	public BigDecimal getCADay(Date date);

}
