package fr.esstin.baresstin.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import fr.esstin.baresstin.dto.BalanceHistoryDto;
import fr.esstin.baresstin.dto.ClientDto;
import fr.esstin.baresstin.dto.YearDto;
import fr.esstin.baresstin.service.exceptions.ClientDoesntExistException;

@Local
public interface ClientServiceLocal {
	
	public boolean add(ClientDto o);
	
	public List<ClientDto> list();
	
	public ClientDto findClientById(long id);
	
	public ClientDto findClientByIdOrCreateGuest(long id, String name);
	
	public ClientDto findClientByLogin(String login)throws ClientDoesntExistException;
	
	public void delete(long id);
	
	public void update(ClientDto c);
	
	public void addBalance(long cId, BigDecimal price, boolean check, String admin);
	
	public List<BalanceHistoryDto> listHistory(long clientId,int start);
	
	public List<YearDto> listYear();
	

}
