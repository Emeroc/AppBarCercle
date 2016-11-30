package fr.esstin.baresstin.actions.admin.orders;

import java.util.List;
import javax.ejb.EJB;

import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.service.OrderServiceLocal;
import fr.esstin.baresstin.service.ProductServiceLocal;

public class ListordersAction {
	
	@EJB
	private OrderServiceLocal orderService;
	
	@EJB
	private ProductServiceLocal productService;
	
	private List<OrderDto> orders;	
	private int pendingOrders;
		
	public String execute() {
		orders = orderService.listPendingOrders();
		
		return "success";
	}

	public List<OrderDto> getOrders() {
		return orders;
	}

	public int getPendingOrders() {
		return pendingOrders;
	}

}
