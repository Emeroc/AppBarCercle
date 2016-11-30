package fr.esstin.baresstin.actions.admin.admin;

import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;

import fr.esstin.baresstin.dto.AdminDto;
import fr.esstin.baresstin.service.AdminServiceLocal;

public class ListAction {
	
	@EJB
	private AdminServiceLocal adminService;
	
	private List<AdminDto> l;
	private String password;
	private String login;
	
	public String execute(){
		setL(adminService.list());
		return "success";
	}
	
	@Action(value = "deleteadmin", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "list") })
	public String deleteadmin() {
		if (password.equals("azerty")){
		Long menuId = new Long(((String[]) ActionContext.getContext()
				.getParameters().get("id"))[0]);
		adminService.delete(menuId.longValue());
		}
		return "success";
	}
	
	@Action(value = "reactivateadmin", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "list") })
	public String reactivateadmin() {
		if (password.equals("azerty")){
		Long menuId = new Long(((String[]) ActionContext.getContext()
				.getParameters().get("id"))[0]);
		adminService.reactivate(menuId.longValue());
		}
		return "success";
	}
	
	@Action(value = "addadmin", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "list") })
	public String addadmin() {
		if (password.equals("azerty")){
		AdminDto a =new AdminDto();
		a.setLogin(login);
		adminService.add(a);
		}
		return "success";
	}

	/**
	 * @return the l
	 */
	public List<AdminDto> getL() {
		return l;
	}

	/**
	 * @param l the l to set
	 */
	public void setL(List<AdminDto> l) {
		this.l = l;
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

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

}
