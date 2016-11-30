package fr.esstin.baresstin.actions.extra;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.dto.OrderItemDto;
import fr.esstin.baresstin.dto.ProductCategoryDto;
import fr.esstin.baresstin.service.OrderServiceLocal;
import fr.esstin.baresstin.service.ProductServiceLocal;

public class ListAction {
	
	@EJB
	private OrderServiceLocal orderService;
	
	@EJB
	private ProductServiceLocal productService;
	
	private List<ProductCategoryDto> categories;
	private Map<OrderItemDto, OrderDto> products;
		
	public String execute() {
		categories = productService.listCategories();
		
		products = new LinkedHashMap<OrderItemDto, OrderDto>();
		List<OrderItemDto> orderItems = orderService.listPendingOrderItems();
		
		for (OrderItemDto oi : orderItems) {
			products.put(oi, orderService.findOrderById(oi.getOrderId()));
		}
		
		return "success";
	}

	public List<ProductCategoryDto> getCategories() {
		return categories;
	}

	public Map<OrderItemDto, OrderDto> getProducts() {
		return products;
	}


}
