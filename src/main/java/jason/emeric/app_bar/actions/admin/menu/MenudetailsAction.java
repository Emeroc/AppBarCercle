package fr.esstin.baresstin.actions.admin.menu;

import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.MenuDto;
import fr.esstin.baresstin.dto.MenuPartDto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.MenuServiceLocal;
import fr.esstin.baresstin.service.ProductServiceLocal;

public class MenudetailsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MenuDto menu;
	
	private List<MenuPartDto> menuparts;
	
	private List<ProductDto> products;
	
	private boolean newmenu;

	@EJB
	private MenuServiceLocal menuService;
	@EJB
	private ProductServiceLocal productService;

	public String execute() {
		try{
		setProducts(productService.list());
		Long menuId = new Long(((String[]) ActionContext.getContext().getParameters().get("id"))[0]);	
		setMenu(menuService.findById(menuId.longValue()));
		setMenuparts(menuService.listByMenuId(menuId.longValue()));
		}
		catch (NullPointerException e)
		{
			newmenu = true;
		}
		return "success";		
	}
	
	@Action(value="updatemenu", results={
			@Result(name="success", type="redirect", location="menu"),
			@Result(name="input", location="menudetails.jsp")
		})
		public String updatemenu() {
		menu.setName(menuService.findById(menu.getId()).getName());
	//		System.out.println("menupart : "+menuparts.get(0).getName());
	//		System.out.println("menupart size : "+menuparts.size());
			menuService.update(menu, menuparts);
			return "success";
		}
	
	@Action(value="addmenu", results={
			@Result(name="success", type="redirect", location="menu"),
			@Result(name="input", location="menudetails.jsp")
		})
		public String addmenu() {
			System.out.println("menu : "+menu.getName());
			//System.out.println("menupart : "+menuparts.get(0).getName());
			//System.out.println("menupart size : "+menuparts.size());
			menuService.add(menu, menuparts);
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

	/**
	 * @return the newmenu
	 */
	public boolean isNewmenu() {
		return newmenu;
	}

	/**
	 * @param newmenu the newmenu to set
	 */
	public void setNewmenu(boolean newmenu) {
		this.newmenu = newmenu;
	}

	/**
	 * @return the menuparts
	 */
	public List<MenuPartDto> getMenuparts() {
		return menuparts;
	}

	/**
	 * @param menuparts the menuparts to set
	 */
	public void setMenuparts(List<MenuPartDto> menuparts) {
		this.menuparts = menuparts;
	}

	/**
	 * @return the products
	 */
	public List<ProductDto> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}


}
