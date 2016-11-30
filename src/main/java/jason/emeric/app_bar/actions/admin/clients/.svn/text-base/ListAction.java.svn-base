package fr.esstin.baresstin.actions.admin.clients;

import java.util.List;

import javax.ejb.EJB;

import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.service.ClientServiceLocal;

public class ListAction {
	
	private List<ClientDto> clients;
	
	@EJB
	private ClientServiceLocal clientService;
	
	public String execute() {
		clients = clientService.list();
		return "success";
	}

	/**
	 * @return the clients
	 */
	public List<ClientDto> getClients() {
		return clients;
	}

	/**
	 * @param clients the clients to set
	 */
	public void setClients(List<ClientDto> clients) {
		this.clients = clients;
	}

}
