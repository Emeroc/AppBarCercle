package fr.esstin.baresstin.dto;

import java.util.List;

public class MenuPartDto {

	private long id;
	private String name;
	private List<ProductDto> products;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
