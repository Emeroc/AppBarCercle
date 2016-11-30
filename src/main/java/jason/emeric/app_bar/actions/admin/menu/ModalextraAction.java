package fr.esstin.baresstin.actions.admin.menu;

import javax.ejb.EJB;

import fr.esstin.baresstin.dto.ExtraDto;
import fr.esstin.baresstin.service.ExtraServiceLocal;

public class ModalextraAction {

	
private ExtraDto extra;
private int id;
	
	@EJB
	private ExtraServiceLocal extraService;
	
	public String execute() {
		try{
			extra = extraService.findById(id);
			}
			catch (NullPointerException e)
			{
				
			}
			return "success";		
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

}
