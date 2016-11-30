package fr.esstin.baresstin.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.esstin.baresstin.Product;
import fr.esstin.baresstin.ProductCategory;
import fr.esstin.baresstin.dao.ProductDao;
import fr.esstin.baresstin.dto.ProductCategoryDto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.exceptions.ProductDoesntExistException;

@Stateless
public class ProductService implements ProductServiceLocal {

	@Inject
	private ProductDao productDao;

	@Override
	public void add(ProductDto p) {
		Product e = new Product();
		fromDto(p, e);
		productDao.save(e);
	}

	@Override
	public Map<ProductCategoryDto, List<ProductDto>> listOrderedByCategory() {
		// bricolage pour mettre des données
		/*if (productDao.findById(1) == null) {
			ProductCategory plat = new ProductCategory();
			plat.setName("Plat");
			plat.setWeight(20);
			plat.setImagePath("plat.png");
			ProductCategory entree = new ProductCategory();
			entree.setName("Entrée");
			entree.setWeight(10);
			entree.setImagePath("entree.png");
			ProductCategory dessert = new ProductCategory();
			dessert.setName("Dessert");
			dessert.setWeight(40);
			dessert.setImagePath("dessert.png");
			ProductCategory boisson = new ProductCategory();
			boisson.setName("Boisson");
			boisson.setWeight(30);
			boisson.setImagePath("boisson.png");
			productDao.saveCategory(plat);
			productDao.saveCategory(entree);
			productDao.saveCategory(dessert);
			productDao.saveCategory(boisson);

			Product coca = new Product();
			coca.setId(1);
			coca.setCategory(boisson);
			coca.setName("Coca");
			coca.setPrice(new BigDecimal("1.2"));
			coca.setAvailable(true);
			coca.setDelivered(true);
			productDao.save(coca);
			Product sandwich = new Product();
			sandwich.setId(2);
			sandwich.setCategory(plat);
			sandwich.setName("Sandwich");
			sandwich.setPrice(new BigDecimal("3.1"));
			sandwich.setAvailable(true);
			productDao.save(sandwich);
			sandwich = new Product();
			sandwich.setId(3);
			sandwich.setCategory(plat);
			sandwich.setName("Jambon beurre");
			sandwich.setPrice(new BigDecimal("2.6"));
			sandwich.setAvailable(true);
			productDao.save(sandwich);
		}*/

		Map<ProductCategoryDto, List<ProductDto>> products = new LinkedHashMap<ProductCategoryDto, List<ProductDto>>();

		for (ProductCategory category : productDao.listCategories()) {
			List<ProductDto> productsForCategory = new ArrayList<ProductDto>();
			for (Product p : productDao.findByCategory(category.getId())) {
				productsForCategory.add(fromEntity(p));
			}

			products.put(fromEntity(category), productsForCategory);
		}

		return products;
	}

	@Override
	public ProductDto findProductById(long id,boolean picture) {
		Product existingProduct = productDao.findById(id);

		if (existingProduct == null) {
			return null;
		}
		if(picture==false)
		return fromEntity(existingProduct);
		else
			return fromEntity(existingProduct);
	}

	@Override
	public void delete(long id) {
		Product existingClient = productDao.findById(id);

		if (existingClient == null) {
			throw new ProductDoesntExistException("Product " + id
					+ " doesn't exist");
		}
		productDao.delete(existingClient);
	}

	@Override
	public List<ProductCategoryDto> listCategories() {
		List<ProductCategoryDto> results = new ArrayList<ProductCategoryDto>();
		List<ProductCategory> entities = productDao.listCategories();

		for (ProductCategory pc : entities) {
			results.add(fromEntity(pc));
		}
		return results;
	}

	private void fromDto(ProductDto p, Product e) {
		e.setId(p.getId());

		// ProductCategory pc = new ProductCategory();
		ProductCategory pc = productDao.findCategoryById(p.getCategory()
				.getId());
		if (pc == null)
			throw new ProductDoesntExistException("Product Category "
					+ p.getCategory().getId() + " doesn't exist");
		// fromDto(p.getCategory(), pc);
		e.setCategory(pc);

		e.setName(p.getName());
		e.setPrice(p.getPrice());
		e.setAvailable(p.getAvailable());
		e.setWeight(p.getWeight());
		e.setDelivered(p.isDelivered());
		if (p.getPicture() != null)
			if (p.getPicture().length > 0)
			{System.out.println("image ok"+p.getPicture());
				e.setPicture(p.getPicture());
			}
	}

	private ProductDto fromEntity(Product e) {
		ProductDto dto = new ProductDto();
		dto.setId(e.getId());
		dto.setCategory(fromEntity(e.getCategory()));
		dto.setName(e.getName());
		dto.setPrice(e.getPrice());
		dto.setAvailable(e.getAvailable());
		dto.setWeight(e.getWeight());
		dto.setEnable(e.isEnable());
		dto.setDelivered(e.isDelivered());
		return dto;
	}
/*
	private ProductDto fromEntityWithPicture(Product e) {
		ProductDto dto = fromEntity(e);
		dto.setPicture(e.getPicture());
		return dto;
	}
	*/
	private void fromDto(ProductCategoryDto pc, ProductCategory e) {
		e.setId(pc.getId());
		e.setName(pc.getName());
		e.setWeight(pc.getWeight());
		e.setImagePath(pc.getImagePath());
	}

	private ProductCategoryDto fromEntity(ProductCategory e) {
		ProductCategoryDto dto = new ProductCategoryDto();
		dto.setId(e.getId());
		dto.setName(e.getName());
		dto.setWeight(e.getWeight());
		dto.setImagePath(e.getImagePath());

		return dto;
	}

	@Override
	public boolean update(ProductDto o) {
		Product p = productDao.findById(o.getId());
		if (p == null)
			throw new ProductDoesntExistException("Product " + o.getId()
					+ " doesn't exist");
		if (p.isEnable() == false)
			throw new ProductDoesntExistException("Product " + o.getId()
					+ " doesn't exist anymore, he is disabled");
		fromDto(o, p);
		productDao.save(p);
		return true;
	}

	@Override
	public List<ProductDto> list() {
		List<Product> products = productDao.list();
		List<ProductDto> pd = new ArrayList<ProductDto>();
		for (Product p : products)
			pd.add(fromEntity(p));
		return pd;

	}

	@Override
	public byte[] getImage(long id) {
		return productDao.getImage(id);
	}

}
