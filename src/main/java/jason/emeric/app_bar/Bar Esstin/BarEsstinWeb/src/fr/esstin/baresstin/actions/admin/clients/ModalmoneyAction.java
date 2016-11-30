package fr.esstin.baresstin.actions.admin.clients;

import java.math.BigDecimal;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.BalanceHistoryDto;
import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.service.ClientServiceLocal;

public class ModalmoneyAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ClientServiceLocal clientsService;

	private BalanceHistoryDto balance;

	private ClientDto client;
	
	private Map<String, Object> session = ActionContext.getContext().getSession();

	@SkipValidation
	public String execute() {
		try {
			Long clientId = new Long(((String[]) ActionContext.getContext()
					.getParameters().get("id"))[0]);
			setClient(clientsService.findClientById(clientId.longValue()));
		} catch (NullPointerException e) {

		}
		return "success";
	}

	@Action(value = "addbalance", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "list") })
	public String deleteproduct() {
		Long clientId = new Long(((String[]) ActionContext.getContext()
				.getParameters().get("id"))[0]);
		/*	client = clientsService.findClientById(clientId);
		if (client.isContributor() == true) {
			if (client.getBalance().compareTo(new BigDecimal(0)) > 0)
				balance.setPrice(balance.getPrice().multiply(
						new BigDecimal(1.1)));
			else {
				BigDecimal b = client.getBalance().add(balance.getPrice());
				if (b.compareTo(new BigDecimal(0)) > 0)
					balance.setPrice(client.getBalance().negate()
							.add(b.multiply(new BigDecimal(1.1))));
			}
		}*/

		boolean check = false;
		if (balance.getStatus() == 4)
			check = true;
		clientsService.addBalance(clientId, balance.getPrice(), check,
				ServletActionContext.getRequest().getRemoteUser());
		session.put("client", clientsService.findClientById(clientId.longValue()));
		return "success";
	}

	public void validate() {
		if (balance == null)
			addActionError(getText("clients.errors.balance"));
		else if (balance.getStatus() < 3 || balance.getStatus() > 4)
			addActionError(getText("clients.errors.balance"));
		else if (balance.getPrice().doubleValue() <= 0)
			addActionError(getText("clients.errors.balance"));
	}

	/**
	 * @return the balance
	 */
	public BalanceHistoryDto getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(BalanceHistoryDto balance) {
		this.balance = balance;
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

}
