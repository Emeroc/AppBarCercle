package fr.esstin.baresstin.actions.admin.orders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.dto.ExtraDto;
import fr.esstin.baresstin.dto.OrderItemDto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.service.ExtraServiceLocal;
import fr.esstin.baresstin.service.OrderServiceLocal;
import fr.esstin.baresstin.service.ProductServiceLocal;

public class ConfirmAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@EJB
	private OrderServiceLocal orderService;

	@EJB
	private ProductServiceLocal productService;

	@EJB
	private ExtraServiceLocal extraService;

	private ClientDto client;
	private List<ProductDto> order;
	private List<OrderItemDto> orderitems;
	private List<ExtraDto> extras;
	private BigDecimal total;

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	@SuppressWarnings("unchecked")
	@Action(results = {
			@Result(name = "chooseclient", type = "redirect", location = "chooseclient"),
			@Result(name = "chooseproducts", type = "redirect", location = "chooseprodcuts") })
	public String execute() {
		if (session.get("client") == null) {
			return "chooseclient";
		}
		if (session.get("order") == null) {
			return "chooseproducts";
		}
		extras = extraService.list();
		client = (ClientDto) session.get("client");
		order = (List<ProductDto>) session.get("order");

		return "success";
	}

	@SuppressWarnings("unchecked")
	@Action(value = "save", results = { @Result(name = "success", type = "redirect", location = "listproducts" ,params={"id","${client.id}","name","${client.name}","total","${total}"}) })
	public String save() {
		client = (ClientDto) session.get("client");
		order = (List<ProductDto>) session.get("order");

		OrderDto o = new OrderDto();
		o.setDatePlaced(new Date());
		o.setStatus("pending");
		o.setClientId(client.getId());
		if (client.getFirstname() != null)
			o.setClientName(client.getName() + " " + client.getFirstname());
		else
			o.setClientName(client.getName());
		o.setOrderItems(new ArrayList<OrderItemDto>());

		for (OrderItemDto oi : orderitems) {
			
			// if (oi.getMenu().getId()>0)
			ProductDto p = productService.findProductById(oi.getProduct()
					.getId(), false);
			BigDecimal price = oi.getProduct().getPrice();
			//System.out.println("price ConfirmAction" + price);
			if (!oi.getComment().equals("")
					&& !oi.getProduct().getName().equals(""))
				oi.setComment(" - " + oi.getComment());
			if (!oi.getProduct().getName().equals(""))
				oi.setComment(oi.getProduct().getName() + oi.getComment());
			if (p != null) {
				oi.setProduct(p);
			}
			oi.setOrderItemPrice(price);

			// orderService.addItemForOrder(o.getId(), oi);
			o.getOrderItems().add(oi);

			// set the order's delivered status
			/*
			 * if (oi.getDelivered()) { orderService.deliverItem(o, oi.getId());
			 * }
			 */
		}
		orderService.add(o, ServletActionContext.getRequest().getRemoteUser());

		session.remove("client");
		session.remove("order");

		return "success";
	}

	public ClientDto getClient() {
		return client;
	}

	public List<ProductDto> getOrder() {
		return order;
	}

	public List<OrderItemDto> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<OrderItemDto> orderitems) {
		this.orderitems = orderitems;
	}

	/**
	 * @return the extras
	 */
	public List<ExtraDto> getExtras() {
		return extras;
	}

	/**
	 * @param extras
	 *            the extras to set
	 */
	public void setExtras(List<ExtraDto> extras) {
		this.extras = extras;
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
