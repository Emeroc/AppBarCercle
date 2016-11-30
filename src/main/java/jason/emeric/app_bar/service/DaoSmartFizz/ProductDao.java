package fr.esstin.baresstin.dao;

import java.util.List;

import fr.esstin.baresstin.Product;
import fr.esstin.baresstin.ProductCategory;

public interface ProductDao {

	public void save(Product p);
	
	public Product findById(long id);
	
	public List<Product> findByCategory(long id);
	
	public ProductCategory findCategoryById(long id);

	public List<Product> list();

	public void delete(Product existingProduct);
	
	public List<ProductCategory> listCategories();
	
	public void saveCategory(ProductCategory pc);
	
	public byte[] getImage(long id);

}
