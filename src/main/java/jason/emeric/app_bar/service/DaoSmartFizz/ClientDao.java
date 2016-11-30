package fr.esstin.baresstin.dao;

import java.util.List;

import fr.esstin.baresstin.BalanceHistory;
import fr.esstin.baresstin.Client;
import fr.esstin.baresstin.Year;

public interface ClientDao {

	public void save(Client c);
	
	public Client findById(long id);
	
	public Client findClientByLogin(String login);
	
	public List<Client> list();

	public void delete(Client existingClient);
	
	public void addBalance(Client c, BalanceHistory b);
	
	public List<BalanceHistory> listHistory(long clientId, int start);
	
	public List<Year> listYear();
	
	public Year findYearById(long id);
	

}
