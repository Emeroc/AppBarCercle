package jason.emeric.app_bar.repository;


import java.util.List;



public interface IProductRepository {

	public void save(ProductEntity e);
	
	public ProductEntity findById(long id);
	
	public List<ProductEntity> findByCategory(long id);
	
	public ProductCategoryEntity findCategoryById(long id);

	public List<ProductEntity> list();

	public void delete(ProductEntity existingProduct);
	
	public List<ProductCategoryEntity> listCategories();
	
	public void saveCategory(ProductCategoryEntity pc);
	
	public byte[] getImage(long id);

}
