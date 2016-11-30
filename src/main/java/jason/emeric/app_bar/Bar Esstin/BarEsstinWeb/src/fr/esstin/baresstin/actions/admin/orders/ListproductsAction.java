package fr.esstin.baresstin.actions.admin.orders;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import fr.esstin.baresstin.Order;
import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.dto.OrderItemDto;
import fr.esstin.baresstin.dto.ProductCategoryDto;
import fr.esstin.baresstin.service.OrderServiceLocal;
import fr.esstin.baresstin.service.ProductServiceLocal;

public class ListproductsAction {
	
	@EJB
	private OrderServiceLocal orderService;
	
	@EJB
	private ProductServiceLocal productService;
	
	private List<ProductCategoryDto> categories;
	private Map<OrderItemDto, OrderDto> products;
	
	private long id;
	private String name;
	private BigDecimal total;
	
	private OrderDto lastorder;
		
	public String execute() {
		categories = productService.listCategories();
		
		products = new LinkedHashMap<OrderItemDto, OrderDto>();
		List<OrderItemDto> orderItems = orderService.listPendingOrderItems();
		lastorder = orderService.listHistory(0,1).get(0);
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

	/**
	 * @return the lastorder
	 */
	public OrderDto getLastorder() {
		return lastorder;
	}

	/**
	 * @param lastorder the lastorder to set
	 */
	public void setLastorder(OrderDto lastorder) {
		this.lastorder = lastorder;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
