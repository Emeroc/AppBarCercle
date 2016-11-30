package fr.esstin.baresstin.dao;

import java.util.List;

import fr.esstin.baresstin.Menu;
import fr.esstin.baresstin.MenuPart;

public interface MenuDao {
	
	public void add(Menu m);
	
	public Menu findById(long id);
	
	public List<Menu> list();
	
	public void update(Menu m);
	
	public void delete(Menu m);
	
	public MenuPart findMenuPartById(long id);
	
	public List<MenuPart> listMenuPart(long id);

}
