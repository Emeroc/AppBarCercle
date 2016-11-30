package fr.esstin.baresstin.dao;

import java.util.List;

import fr.esstin.baresstin.Extra;

public interface ExtraDao {
	
	public void save(Extra e);
	public void delete(long id);
	public List<Extra> list();
	public Extra findById(long id);

}
