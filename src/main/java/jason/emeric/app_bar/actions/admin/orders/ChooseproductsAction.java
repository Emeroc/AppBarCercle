package fr.esstin.baresstin.actions.admin.orders;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.dto.MenuDto;
import fr.esstin.baresstin.dto.MenuPartDto;
import fr.esstin.baresstin.dto.ProductCategoryDto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.MenuServiceLocal;
import fr.esstin.baresstin.service.ProductServiceLocal;

public class ChooseproductsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private ClientDto client;
	private long menuId;
	private List<MenuDto> menus;
	private Map<ProductCategoryDto, List<ProductDto>> products;
	private List<ProductDto> order;

	@EJB
	private ProductServiceLocal productService;

	@EJB
	private MenuServiceLocal menuService;

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	@SuppressWarnings("unchecked")
	@SkipValidation
	@Action(results = { @Result(name = "chooseclient", type = "redirect", location = "chooseclient") })
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if (session.get("client") == null || request.getHeader("referer") == null) {
			return "chooseclient";
		}

		client = (ClientDto) session.get("client");
		products = productService.listOrderedByCategory();
		menus = menuService.list();
		//System.out.println("menus list : " + menus.size());

		if (session.get("order") != null) {
			order = (List<ProductDto>) session.get("order");
		}

		return "success";
	}

	@Action(value = "saveproducts", results = {
			@Result(name = "success", type = "redirect", location = "confirm"),
			@Result(name = "input", location = "chooseproducts.jsp") })
	public String saveproducts() {
		// Get Dto from product id
		for (int i = 0; i < order.size(); i++) {
			ProductDto item = order.get(i);
			// System.out.println("je suis passé ici"+item.getId());
			if (item.getId() != -1) {
				// System.out.println("Item is not a menu");
				// Item is not a menu
				if (item.getPrice().compareTo(BigDecimal.ZERO) == 0) {
					// Item is a menupart
					// System.out.println("Item is a menu part");
					order.set(i,
							productService.findProductById(item.getId(), false));
					order.get(i).setPrice(new BigDecimal(0));

				} else {
					// Item is a regular item
					// System.out.println("Item is a regular item");
					item = productService.findProductById(item.getId(), false);
					// item.setPrice(new BigDecimal(0));
					order.set(i, item);
				}
			} else {
				// System.out.println("Item is a menu");
				// Item is a menu
				item.setDelivered(true);
				order.set(i, item);
			}
		}
		session.put("order", order);

		return "success";
	}

	public void validate() {
		if (order == null || order.size() == 0) {
			client = (ClientDto) session.get("client");
			products = productService.listOrderedByCategory();

			addActionError(getText("orders.new.chooseproducts.error"));
		} 
	}

	@SkipValidation
	@Action(value = "choosemenu")
	public String choosemenu() {
		List<MenuPartDto> menu = menuService.listByMenuId(menuId);

		products = new LinkedHashMap<ProductCategoryDto, List<ProductDto>>();
		for (MenuPartDto mp : menu) {
			// Fake category to show part name
			ProductCategoryDto fakeCategory = new ProductCategoryDto();
			fakeCategory.setName(mp.getName());

			products.put(fakeCategory, mp.getProducts());
		}

		return "success";
	}

	public ClientDto getClient() {
		return client;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public List<MenuDto> getMenus() {
		return menus;
	}

	public Map<ProductCategoryDto, List<ProductDto>> getProducts() {
		return products;
	}

	public List<ProductDto> getOrder() {
		return order;
	}

	public void setOrder(List<ProductDto> order) {
		this.order = order;
	}

}
