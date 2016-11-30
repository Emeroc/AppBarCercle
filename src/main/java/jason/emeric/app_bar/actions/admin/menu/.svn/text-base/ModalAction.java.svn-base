package fr.esstin.baresstin.actions.admin.menu;

import javax.ejb.EJB;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.MenuDto;
import fr.esstin.baresstin.service.MenuServiceLocal;

public class ModalAction  extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MenuDto menu; 
	
	@EJB
	private MenuServiceLocal menuService;
	
	public String execute() {
		try{
			Long menuId = new Long(((String[]) ActionContext.getContext().getParameters().get("id"))[0]);		
			setMenu(menuService.findById(menuId.longValue()));
			}
			catch (NullPointerException e)
			{
				
			}
			return "success";		
	}

	/**
	 * @return the menu
	 */
	public MenuDto getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(MenuDto menu) {
		this.menu = menu;
	}

}
