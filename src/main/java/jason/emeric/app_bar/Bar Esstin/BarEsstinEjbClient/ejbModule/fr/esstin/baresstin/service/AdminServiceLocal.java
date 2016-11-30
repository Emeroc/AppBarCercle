package fr.esstin.baresstin.service;

import java.util.List;

import javax.ejb.Local;

import fr.esstin.baresstin.dto.AdminDto;

@Local
public interface AdminServiceLocal {
	
	public void add(AdminDto a);
	public void update (long id);
	public void delete (long id);
	public List<AdminDto> list();
	public AdminDto findById(long id);
	public AdminDto findByLogin(String login);
	public void reactivate(long longValue);

}
