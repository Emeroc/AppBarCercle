package fr.esstin.baresstin.actions.admin.clients;

import java.util.List;

import javax.ejb.EJB;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.dto.YearDto;
import fr.esstin.baresstin.service.ClientServiceLocal;

public class AddAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClientDto client;
	
	private List<YearDto> l;
	
	@EJB
	private ClientServiceLocal clientService;
	
	@SkipValidation
	public String execute() {	
		setL(clientService.listYear());
		return "success";
	}

	@Action(value = "addclient", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "add.jsp") })
	public String addclient() {
		clientService.add(client);
		return "success";
	}

	public void validate() {
		setL(clientService.listYear());
		if (client.getName()==null || client.getName().equals(""))
			addActionError(getText("clients.errors.name"));
		if (client.getFirstname()==null || client.getFirstname().equals(""))
			addActionError(getText("clients.errors.firstname"));
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
	 * @return the l
	 */
	public List<YearDto> getL() {
		return l;
	}

	/**
	 * @param l the l to set
	 */
	public void setL(List<YearDto> l) {
		this.l = l;
	}

}
