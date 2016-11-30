package fr.esstin.baresstin.actions.admin.orders;

import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.service.OrderServiceLocal;

public class HistoryAction {
	
	@EJB
	private OrderServiceLocal orderService;
	
	private List<OrderDto> orders;
	private int page = 1;	
	private OrderDto order;
	private long id;
	
	private static final int ORDERS_PER_PAGE = 20;	
	
	public String execute() {
		
		//page = Math.min(1, page);
		orders = orderService.listHistory((page - 1) * ORDERS_PER_PAGE, ORDERS_PER_PAGE);
		
		return "success";
	}
	
	@Action(value="historydetails")
	public String save() {
		order = orderService.findOrderById(id);

		return "success";
	}

	public List<OrderDto> getOrders() {
		return orders;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public OrderDto getOrder() {
		return order;
	}
	
	public void setId(long id) {
		this.id = id;
	}

}
