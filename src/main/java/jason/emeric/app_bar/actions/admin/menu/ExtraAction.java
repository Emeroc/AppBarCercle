package fr.esstin.baresstin.actions.admin.menu;

import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;

import fr.esstin.baresstin.dto.ExtraDto;
import fr.esstin.baresstin.service.ExtraServiceLocal;

public class ExtraAction {
	
	private List<ExtraDto> l;
	
	@EJB
	private ExtraServiceLocal extraService;
	
	public String execute() {
		setL(extraService.list());
		return "success";

	}

	@Action(value = "deleteextra", results = {
			@Result(name = "success", type = "redirect", location = "extra"),
			@Result(name = "input", location = "extra") })
	public String deleteproduct() {
		Long menuId = new Long(((String[]) ActionContext.getContext()
				.getParameters().get("id"))[0]);
		extraService.delete(menuId.longValue());
		return "success";
	}
	/**
	 * @return the l
	 */
	public List<ExtraDto> getL() {
		return l;
	}

	/**
	 * @param l the l to set
	 */
	public void setL(List<ExtraDto> l) {
		this.l = l;
	}

}
