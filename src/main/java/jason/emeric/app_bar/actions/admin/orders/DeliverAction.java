package fr.esstin.baresstin.actions.admin.orders;

import javax.ejb.EJB;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.dto.OrderItemDto;
import fr.esstin.baresstin.service.OrderServiceLocal;

@Result(name="success", type="redirect", location="listorders")
public class DeliverAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@EJB
	private OrderServiceLocal orderService;

	private long orderId;
	private Long itemId;

	public String execute() {
		OrderDto o = orderService.findOrderById(orderId);
		
		if (o != null) {
			if (itemId == null) {
				for (OrderItemDto oi : o.getOrderItems()) {
					orderService.deliverItem(o, oi.getId());
				}
			} else {
				orderService.deliverItem(o, itemId);
			}
		}

		return "success";
	}
	
	@Action(value="canceldelivery", results={
		@Result(name="success", type="redirect", location="listproducts")
	})
	public String canceldelivery() {
		OrderDto o = orderService.findOrderById(orderId);
		
		if (o != null) {
			orderService.cancelDeliverItem(o, itemId);
		}
		
		return "success";
	}
	
	@Action(value="removeitem", results={
		@Result(name="success", type="redirect", location="listproducts")	
	})
	public String removeitem() {
		OrderDto o = orderService.findOrderById(orderId);
		
		if (o != null) {
			orderService.removeItem(o, itemId,ServletActionContext.getRequest().getRemoteUser());
		}
		
		return "success";
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}
