package fr.esstin.baresstin.actions.admin.clients;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.dto.YearDto;
import fr.esstin.baresstin.service.ClientServiceLocal;

public class ModalclienteditAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ClientDto client;
	private List<YearDto> l;
	
	@EJB
	private ClientServiceLocal clientsService;
	
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	@SkipValidation
	public String execute() {
		try{
			Long clientId = new Long(((String[]) ActionContext.getContext().getParameters().get("id"))[0]);		
			setClient(clientsService.findClientById(clientId.longValue()));
			setL(clientsService.listYear());
			}
			catch (NullPointerException e)
			{
				
			}
			return "success";		
	}
	
	@Action(value = "updateclient", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "modaledit.jsp") })
	public String deleteproduct() {
		Long clientId = new Long(((String[]) ActionContext.getContext()
				.getParameters().get("id"))[0]);
		client.setId(clientId);
		clientsService.update(client);
		session.put("client", clientsService.findClientById(clientId.longValue()));
		return "success";
	}

	public void validate() {
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
