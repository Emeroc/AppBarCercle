package fr.esstin.baresstin.actions.admin.clients;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.service.LDAPServiceLocal;

@ParentPackage("json-default")
@Result(type = "json")
public class AjaxLDAPAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientDto client;
	
	@EJB
	private LDAPServiceLocal ldapService;
	
	@Action(value="ajaxldap")
	public String execute() {
		try
		{
			String login = new String(((String[]) ActionContext.getContext().getParameters().get("login"))[0]);
			System.out.println("wxcvbn : "+login.toString());
			setClient(ldapService.getInfo(login));
			System.out.println("wxcvbn : "+client.getFirstname());
		}
		catch (NullPointerException e)
		{
			System.out.println("failed");
		}
		System.out.println("next");
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

}
