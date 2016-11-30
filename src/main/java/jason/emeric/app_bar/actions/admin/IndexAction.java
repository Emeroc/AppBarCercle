package fr.esstin.baresstin.actions.admin;

import javax.ejb.EJB;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import fr.esstin.baresstin.service.AdminServiceLocal;

public class IndexAction {

	@EJB
	private AdminServiceLocal adminService;

	private boolean admin;

	@Action(value = "/")
	public String execute() {
		try {
			if (adminService.findByLogin(ServletActionContext.getRequest()
					.getRemoteUser()) != null) {
				admin = true;
			}

		} catch (Exception e) {

		}
		return "success";
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
