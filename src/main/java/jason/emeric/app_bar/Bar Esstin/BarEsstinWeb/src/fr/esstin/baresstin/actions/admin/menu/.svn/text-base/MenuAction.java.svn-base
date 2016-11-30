package fr.esstin.baresstin.actions.admin.menu;

import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.MenuDto;
import fr.esstin.baresstin.service.MenuServiceLocal;

public class MenuAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<MenuDto> menus;

	@EJB
	private MenuServiceLocal menuService;

	public String execute() {
		setMenus(menuService.list());
		return "success";

	}

	@Action(value = "deletemenu", results = {
			@Result(name = "success", type = "redirect", location = "menu"),
			@Result(name = "input", location = "menu") })
	public String deleteproduct() {
		Long menuId = new Long(((String[]) ActionContext.getContext()
				.getParameters().get("id"))[0]);
		menuService.delete(menuId.longValue());
		return "success";
	}

	/**
	 * @return the menus
	 */
	public List<MenuDto> getMenus() {
		return menus;
	}

	/**
	 * @param menus
	 *            the menus to set
	 */
	public void setMenus(List<MenuDto> menus) {
		this.menus = menus;
	}

}
