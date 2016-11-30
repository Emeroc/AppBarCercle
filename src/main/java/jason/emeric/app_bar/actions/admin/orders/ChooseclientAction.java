package fr.esstin.baresstin.actions.admin.orders;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.service.ClientServiceLocal;

public class ChooseclientAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private long clientId;
	private String clientName;
	private String guestName;
	private String submitType;
	private List<ClientDto> clients;
	private String password;
	
	@EJB
	private ClientServiceLocal clientService;

	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	@SkipValidation
	public String execute() {
		clients = clientService.list();
		return "success";
	}
	
	@Action(value="saveclient", results={
		@Result(name="success", type="redirect", location="chooseproducts"),
		@Result(name="input", location="chooseclient.jsp")
	})
	public String saveclient() {
		ClientDto c =clientService.findClientByIdOrCreateGuest(clientId, clientName);
		
					
				
		session.put("client", c);
		session.remove("order");
		
		return "success";
	}
	
	public void validate() {
		clients = clientService.list();
		ClientDto c =clientService.findClientByIdOrCreateGuest(clientId, clientName);
		if(c.getBalance()!=null)
			if(c.getBalance().doubleValue()<-10)
				if (!password.equals(getText("password")))
					addActionError(getText("orders.new.chooseclient.nopassword"));
		
		if (submitType.equals("client")) {
			if (clientId <= 0 || clientName == null || clientName.equals("")) {
				addActionError(getText("orders.new.chooseclient.error"));
			} else if (clientService.findClientById(clientId) == null) {
				addActionError(getText("orders.new.chooseclient.noclient"));
			}
		} else {
			if (guestName == null || guestName.equals("")) {
				addActionError(getText("orders.new.chooseclient.error"));				
			} else {
				clientId = -1;
				clientName = guestName;
			}
		}
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public String getGuestName() {
		return guestName;
	}
	
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public List<ClientDto> getClients() {
		return clients;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}