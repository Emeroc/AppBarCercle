package fr.esstin.baresstin.actions.image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.service.ProductServiceLocal;

@Result(name = "success", type = "stream", params = { "contentType",
		"image/jpg", 
		"inputName", "imageStream","filename","img.jpg" })
public class ImgAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream imageStream;
	private int streamLength;
	private long id;
	
	@EJB ProductServiceLocal productService;

	@Override
	public String execute()
	{
		try{
			imageStream = new ByteArrayInputStream(productService.getImage(id));
		}
		catch (Exception e)
		{
			imageStream = null;
		}
		//setImageStream(productService.getImage(id));
		return "success";
	}


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
	 * @return the imageStream
	 */
	public InputStream getImageStream() {
		return imageStream;
	}


	/**
	 * @param imageStream the imageStream to set
	 */
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}


	/**
	 * @return the streamLength
	 */
	public int getStreamLength() {
		return streamLength;
	}


	/**
	 * @param streamLength the streamLength to set
	 */
	public void setStreamLength(int streamLength) {
		this.streamLength = streamLength;
	}


}
