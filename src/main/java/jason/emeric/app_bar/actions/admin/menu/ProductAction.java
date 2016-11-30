package fr.esstin.baresstin.actions.admin.menu;

import static org.imgscalr.Scalr.OP_ANTIALIAS;
import static org.imgscalr.Scalr.resize;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import javax.ejb.EJB;
import javax.imageio.ImageIO;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.imgscalr.Scalr.Method;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ProductCategoryDto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.ProductServiceLocal;

public class ProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ProductServiceLocal productService;

	private List<ProductCategoryDto> l;

	private ProductDto product;

	private boolean newproduct;

	private File file;
	private String contentType;
	private String filename;

	@SkipValidation
	public String execute() {
		try {
			Long productId = new Long(((String[]) ActionContext.getContext()
					.getParameters().get("id"))[0]);
			setProduct(productService.findProductById(productId.longValue(),true));
		} catch (NullPointerException e) {
			newproduct = true;
		}
		l = productService.listCategories();
		return "success";
	}

	@Action(value = "updateproduct", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "product.jsp") })
	public String updateproduct() {
		// session.put("client",
		// clientService.findClientByIdOrCreateGuest(clientId, clientName));
		if (file != null)
			try {
				BufferedImage srcImage = ImageIO.read(file);
				if (srcImage != null) {

					srcImage = resize(srcImage, Method.ULTRA_QUALITY, 300,
							OP_ANTIALIAS);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(srcImage, "jpg", baos); // if your image is a
															// jpg
					baos.flush();
					byte[] imageInByte = baos.toByteArray();
					baos.close();
					//imageInByte = new Base64().encode(imageInByte);
					product.setPicture(imageInByte);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (file.exists())
					file.delete();
			}
		product.setName(productService.findProductById(product.getId(),false)
				.getName());
		productService.update(product);
		return "success";
	}

	@Action(value = "addproduct", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "product.jsp") })
	public String addproduct() {
		System.out.println("path file : " + file);
		System.out.println("filename  : " + contentType);
		if (file != null)
			try {
				BufferedImage srcImage = ImageIO.read(file);
				if (srcImage != null) {

					srcImage = resize(srcImage, Method.ULTRA_QUALITY, 300,
							OP_ANTIALIAS);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(srcImage, "jpg", baos); // if your image is a
															// jpg
					baos.flush();
					byte[] imageInByte = baos.toByteArray();
					baos.close();
					//imageInByte = new Base64().encode(imageInByte);
					product.setPicture(imageInByte);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (file.exists())
					file.delete();
			}
		// session.put("client",
		// clientService.findClientByIdOrCreateGuest(clientId, clientName));
		productService.add(product);
		return "success";
	}

	@Action(value = "deleteproduct", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "product.jsp") })
	public String deleteproduct() {
		// session.put("client",
		// clientService.findClientByIdOrCreateGuest(clientId, clientName));
		// product.setName(productService.findProductById(product.getId()).getName());
		// productService.update(product);
		Long productId = new Long(((String[]) ActionContext.getContext()
				.getParameters().get("id"))[0]);
		productService.delete(productId.longValue());
		return "success";
	}

	/*
	 * public void validate() {
	 * 
	 * if (product.getName().length() == 0) addFieldError("product.name",
	 * "First name is required.");
	 * 
	 * /* if ( personBean.getFirstName().length() == 0 ){
	 * 
	 * addFieldError( "personBean.firstName", "First name is required." );
	 * 
	 * }
	 * 
	 * 
	 * if ( personBean.getEmail().length() == 0 ){
	 * 
	 * addFieldError( "personBean.email", "Email is required." );
	 * 
	 * }
	 * 
	 * if ( personBean.getAge() < 18 ){
	 * 
	 * addFieldError( "personBean.age",
	 * "Age is required and must be 18 or older" );
	 * 
	 * }
	 */
	/*
	 * }
	 */
	/**
	 * @return the product
	 */
	public ProductDto getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(ProductDto product) {
		this.product = product;
	}

	/**
	 * @return the l
	 */
	public List<ProductCategoryDto> getL() {
		return l;
	}

	/**
	 * @param l
	 *            the l to set
	 */
	public void setL(List<ProductCategoryDto> l) {
		this.l = l;
	}

	/**
	 * @return the newproduct
	 */
	public boolean isNewproduct() {
		return newproduct;
	}

	/**
	 * @param newproduct
	 *            the newproduct to set
	 */
	public void setNewproduct(boolean newproduct) {
		this.newproduct = newproduct;
	}

	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}
}
