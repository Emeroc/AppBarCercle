package jason.emeric.app_bar.service;


import java.util.List;
import java.util.Map;
import javax.ejb.Local;

import jason.emeric.app_bar.service.model.ProductCategoryDto;
import jason.emeric.app_bar.service.model.ProductDto;



@Local
public interface IProductService {
	
	public void add(ProductDto o);
	
	public boolean update(ProductDto o );
	
	public Map<ProductCategoryDto, List<ProductDto>> listOrderedByCategory();
	
	public ProductDto findProductById(long id,boolean picture);
	
	public void delete(long id);
	
	public List<ProductCategoryDto> listCategories();
	
	public List<ProductDto> list();

	public byte[] getImage(long id);
}
