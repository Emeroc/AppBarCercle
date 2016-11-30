package fr.esstin.baresstin.actions.admin.clients;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.service.ClientServiceLocal;

public class ModaldeleteAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClientDto client;
	private String password;
	
	
	@EJB
	private ClientServiceLocal clientsService;
	public String execute() {
		try{
			Long clientId = new Long(((String[]) ActionContext.getContext().getParameters().get("id"))[0]);		
			setClient(clientsService.findClientById(clientId.longValue()));
			}
			catch (NullPointerException e)
			{
				
			}
			return "success";		
	}
	
	@Action(value = "deleteclient", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "list") })
	public String deleteclient() {
		if (password.equals(getText("password"))){
		Long clientId = new Long(((String[]) ActionContext.getContext()
				.getParameters().get("id"))[0]);
		clientsService.delete(clientId.longValue());
		}
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
