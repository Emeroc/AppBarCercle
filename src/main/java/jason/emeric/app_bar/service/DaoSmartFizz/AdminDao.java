package fr.esstin.baresstin.dao;

import java.util.List;

import fr.esstin.baresstin.Admin;

public interface AdminDao {
	
	public void add(Admin a);
	public void update (Admin a);
	public List<Admin> list();
	public Admin findById(long id);
	public void delete(long id);
	public Admin findByLogin(String login);
	public void reactivate(long longValue);
}
