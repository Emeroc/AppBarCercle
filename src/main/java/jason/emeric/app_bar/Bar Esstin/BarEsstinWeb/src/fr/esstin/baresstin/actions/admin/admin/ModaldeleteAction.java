package fr.esstin.baresstin.actions.admin.admin;

import javax.ejb.EJB;

import fr.esstin.baresstin.dto.AdminDto;
import fr.esstin.baresstin.service.AdminServiceLocal;

public class ModaldeleteAction {

	private AdminDto admin;
	private long id;

	@EJB
	private AdminServiceLocal adminService;

	public String execute() {
		try {
			setAdmin(adminService.findById(id));
		} catch (NullPointerException e) {

		}
		return "success";
	}

	/**
	 * @return the admin
	 */
	public AdminDto getAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(AdminDto admin) {
		this.admin = admin;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
