package fr.esstin.baresstin.actions.admin.menu;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;

import fr.esstin.baresstin.dto.ProductCategoryDto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.ProductServiceLocal;

public class ListAction {

	private Map<ProductCategoryDto, List<ProductDto>> products;

	@EJB
	private ProductServiceLocal productService;

	public String execute() {
		setProducts(productService.listOrderedByCategory());
		/*List<ProductDto> l = productService.list();
		for (ProductDto p : l)
			if (p.getPictureS() != null)
				if (p.getPicture().length > 0) {
					
					p.setPicture(new Base64().decode(p.getPicture()));
					productService.update(p);
				}*/
		return "success";

	}

	/**
	 * @return the products
	 */
	public Map<ProductCategoryDto, List<ProductDto>> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(Map<ProductCategoryDto, List<ProductDto>> products) {
		this.products = products;
	}
}
