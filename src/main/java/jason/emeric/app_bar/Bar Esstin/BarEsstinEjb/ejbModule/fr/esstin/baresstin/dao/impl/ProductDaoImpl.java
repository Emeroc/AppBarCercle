package fr.esstin.baresstin.dao.impl;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.esstin.baresstin.Product;
import fr.esstin.baresstin.ProductCategory;
import fr.esstin.baresstin.dao.ProductDao;

public class ProductDaoImpl implements ProductDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Product p) {
		p.setEnable(true);
		em.persist(p);
	}

	@Override
	public Product findById(long id) {
		TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p WHERE p.id=:id", Product.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<Product> findByCategory(long id) {
		TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p WHERE p.category.id=:id AND p.enable=true order by p.available DESC,p.weight DESC", Product.class);
		q.setParameter("id", id);
		return q.getResultList();
	}

	@Override
	public List<Product> list() {
		TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p WHERE p.enable=true", Product.class);
		return q.getResultList();
	}

	@Override
	public void delete(Product existingProduct) {
		//em.remove(existingProduct);
		existingProduct.setEnable(false);
		em.persist(existingProduct);
	}
	
	@Override
	public List<ProductCategory> listCategories() {
		TypedQuery<ProductCategory> q = em.createQuery("SELECT pc FROM ProductCategory pc ORDER BY pc.weight ASC", ProductCategory.class);
		return q.getResultList();
	}
	
	@Override
	public void saveCategory(ProductCategory pc) {
		em.persist(pc);
	}

	@Override
	public ProductCategory findCategoryById(long id) {
		TypedQuery<ProductCategory> q = em.createQuery("SELECT p FROM ProductCategory p WHERE p.id=:id", ProductCategory.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public byte[] getImage(long id) {
		TypedQuery<Product> q = em.createQuery("SELECT p FROM Product p WHERE p.id=:id", Product.class);
		q.setParameter("id", id);
		try {
			return q.getSingleResult().getPicture();
		}
		catch (NoResultException e) {
			return null;
		} 
	}
}