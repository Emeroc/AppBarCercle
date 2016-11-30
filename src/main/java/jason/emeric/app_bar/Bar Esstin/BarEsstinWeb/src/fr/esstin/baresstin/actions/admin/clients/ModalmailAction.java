package fr.esstin.baresstin.actions.admin.clients;

import java.util.List;

import javax.ejb.EJB;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.service.ClientServiceLocal;
import fr.esstin.baresstin.service.MailServiceLocal;

public class ModalmailAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subject;
	private String text;

	@EJB
	private MailServiceLocal mailService;

	@EJB
	private ClientServiceLocal clientServiceLocal;

	public String execute() {
		return "success";
	}

	@Action(value = "sendmail", results = {
			@Result(name = "success", type = "redirect", location = "list"),
			@Result(name = "input", location = "list.jsp") })
	public String sendmail() {
		System.out.println("subject"+subject);
		System.out.println("text"+text);
		
		List<ClientDto> l = clientServiceLocal.list();
		for (ClientDto c : l) {
			if (c.getBalance().doubleValue() < 0)
				if (c.getMail() != null)
					if (c.isEnable() == true)
						mailService.sendMail(c.getMail(),
								c.getName() + "" + c.getFirstname(), subject,
								text,null);
		}
		return "success";
	}
	
	public void validate() {
		
		if (subject==null || subject.equals(""))
			addActionError(getText("clients.errors.subject"));
		if (text==null || text.equals(""))
			addActionError(getText("clients.errors.text"));
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

}
