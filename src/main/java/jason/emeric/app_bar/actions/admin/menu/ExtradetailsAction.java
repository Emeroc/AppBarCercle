package fr.esstin.baresstin.actions.admin.menu;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import fr.esstin.baresstin.dto.ExtraDto;
import fr.esstin.baresstin.service.ExtraServiceLocal;

public class ExtradetailsAction {
	
	private int id;
	private ExtraDto extra;
	private boolean newextra;
	
	@EJB
	private ExtraServiceLocal extraService;
	
	public String execute()
	{
		//if(id!=0)
			extra=extraService.findById(id);
		if(extra == null)
			newextra=true;
		return "success";
	}

	
	@Action(value="updateextra", results={
			@Result(name="success", type="redirect", location="extra"),
			@Result(name="input", location="extradetails.jsp")
		})
		public String updatemenu() {
		//menu.setName(menuService.findById(menu.getId()).getName());
	//		System.out.println("menupart : "+menuparts.get(0).getName());
	//		System.out.println("menupart size : "+menuparts.size());
			extraService.update(extra);
			return "success";
		}
	
	@Action(value="addextra", results={
			@Result(name="success", type="redirect", location="extra"),
			@Result(name="input", location="extradetails.jsp")
		})
		public String addmenu() {
			//System.out.println("menu : "+menu.getName());
			//System.out.println("menupart : "+menuparts.get(0).getName());
			//System.out.println("menupart size : "+menuparts.size());
		extraService.add(extra);
			return "success";
		}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the extra
	 */
	public ExtraDto getExtra() {
		return extra;
	}

	/**
	 * @param extra the extra to set
	 */
	public void setExtra(ExtraDto extra) {
		this.extra = extra;
	}

	/**
	 * @return the newextra
	 */
	public boolean isNewextra() {
		return newextra;
	}

	/**
	 * @param newextra the newextra to set
	 */
	public void setNewextra(boolean newextra) {
		this.newextra = newextra;
	}
	
	

}
