package fr.esstin.baresstin.actions.admin.clients;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.BalanceHistoryDto;
import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.service.ClientServiceLocal;
import fr.esstin.baresstin.service.OrderServiceLocal;

public class ClienthistoryAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClientDto client;
	
	private static final int ORDERS_PER_PAGE = 20;
	
	private int page = 1;
	
	private List<BalanceHistoryDto> lbalancehistory;
	
	@EJB
	private ClientServiceLocal clientsService;
	
	@EJB
	private OrderServiceLocal orderService;
	
	private OrderDto order;
	
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	public String execute() {
		try{
			
			Long clientId = new Long(((String[]) ActionContext.getContext().getParameters().get("id"))[0]);		
			setClient(clientsService.findClientById(clientId.longValue()));
			session.put("client", client);
			lbalancehistory = clientsService.listHistory(clientId, (page-1)
					* ORDERS_PER_PAGE);
			System.out.println("size"+lbalancehistory.size());
			}
			catch (NullPointerException e)
			{
				e.printStackTrace();
			}
			return "success";		
	}

	@Action(value = "historydetails")
	public String historydetails() {
		Long orderId = new Long(((String[]) ActionContext.getContext().getParameters().get("id"))[0]);	
		if (orderId == 0)
			return "success";

		setOrder(orderService.findOrderById(orderId));
		return "success";
	}
	/**
	 * @return the client
	 */
	public ClientDto getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(ClientDto client) {
		this.client = client;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the lbalancehistory
	 */
	public List<BalanceHistoryDto> getLbalancehistory() {
		return lbalancehistory;
	}

	/**
	 * @param lbalancehistory the lbalancehistory to set
	 */
	public void setLbalancehistory(List<BalanceHistoryDto> lbalancehistory) {
		this.lbalancehistory = lbalancehistory;
	}

	/**
	 * @return the order
	 */
	public OrderDto getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(OrderDto order) {
		this.order = order;
	}



}
