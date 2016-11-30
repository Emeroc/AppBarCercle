package fr.esstin.baresstin.service;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;

import fr.esstin.baresstin.dto.ProductCategoryDto;
import fr.esstin.baresstin.dto.ProductDto;

@Local
public interface ProductServiceLocal {
	
	public void add(ProductDto o);
	
	public boolean update(ProductDto o );
	
	public Map<ProductCategoryDto, List<ProductDto>> listOrderedByCategory();
	
	public ProductDto findProductById(long id,boolean picture);
	
	public void delete(long id);
	
	public List<ProductCategoryDto> listCategories();
	
	public List<ProductDto> list();

	public byte[] getImage(long id);
}
