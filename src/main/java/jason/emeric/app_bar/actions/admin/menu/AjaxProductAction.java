package fr.esstin.baresstin.actions.admin.menu;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.ProductServiceLocal;

@ParentPackage("json-default")
@Result(type = "json")
public class AjaxProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProductDto product;
	
	@EJB
	ProductServiceLocal productService;
	
	@Action(value="ajaxproduct")
	public String execute() {
		try
		{
			Long productId = new Long(((String[]) ActionContext.getContext().getParameters().get("id"))[0]);
			System.out.println("wxcvbn : "+productId.toString());
			setProduct(productService.findProductById(productId.longValue(),false));
		}
		catch (NullPointerException e)
		{
			System.out.println("failed");
		}
		System.out.println("next");
        return "success";
 }

	/**
	 * @return the product
	 */
	public ProductDto getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ProductDto product) {
		this.product = product;
	}

}
