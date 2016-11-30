package fr.esstin.baresstin.service;

import javax.ejb.Local;

import fr.esstin.baresstin.dto.ClientDto;

@Local
public interface LDAPServiceLocal {
	
	public ClientDto getInfo(String login);

}
