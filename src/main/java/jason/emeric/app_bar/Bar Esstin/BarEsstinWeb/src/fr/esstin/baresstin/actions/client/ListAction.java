package fr.esstin.baresstin.actions.client;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import fr.esstin.baresstin.dto.BalanceHistoryDto;
import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.dto.OrderDto;
import fr.esstin.baresstin.service.ClientServiceLocal;
import fr.esstin.baresstin.service.OrderServiceLocal;

public class ListAction {

	@EJB
	private ClientServiceLocal clientService;
	@EJB
	private OrderServiceLocal orderService;

	private List<BalanceHistoryDto> lbalanceHistory;
	private int page = 1;
	private OrderDto order;
	private long id;
	private ClientDto client;
	private String name;

	private static final int ORDERS_PER_PAGE = 20;

	//private HttpServletRequest request = ServletActionContext.getRequest();

	public String execute() {

		// page = Math.min(1, page);
		try {
			name = ServletActionContext.getRequest().getRemoteUser();
			client = clientService.findClientByLogin(name);
			lbalanceHistory = clientService.listHistory(client.getId(),
					(page - 1) * ORDERS_PER_PAGE);
		} catch (Exception e) {
			lbalanceHistory = new ArrayList<BalanceHistoryDto>();
		}
		return "success";
	}

	@Action(value = "historydetails")
	public String historydetails() {
		if (id == 0)
			return "success";

		order = orderService.findOrderById(id);
		return "success";
	}

	/**
	 * @return the lbalanceHistory
	 */
	public List<BalanceHistoryDto> getLbalanceHistory() {
		return lbalanceHistory;
	}

	/**
	 * @param lbalanceHistory
	 *            the lbalanceHistory to set
	 */
	public void setLbalanceHistory(List<BalanceHistoryDto> lbalanceHistory) {
		this.lbalanceHistory = lbalanceHistory;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the order
	 */
	public OrderDto getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(OrderDto order) {
		this.order = order;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the client
	 */
	public ClientDto getClient() {
		return client;
	}

	/**
	 * @param client
	 *            the client to set
	 */
	public void setClient(ClientDto client) {
		this.client = client;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
