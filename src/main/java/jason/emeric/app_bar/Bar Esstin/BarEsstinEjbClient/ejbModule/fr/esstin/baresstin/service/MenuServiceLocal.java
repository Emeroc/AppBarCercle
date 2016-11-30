package fr.esstin.baresstin.service;

import java.util.List;

import javax.ejb.Local;

import fr.esstin.baresstin.dto.MenuDto;
import fr.esstin.baresstin.dto.MenuPartDto;
import fr.esstin.baresstin.dto.ProductDto;

@Local
public interface MenuServiceLocal {
	
	public void update(MenuDto m,List<MenuPartDto> menuParts);
	public void delete(long id);
	public List<MenuDto> list();
	public List<MenuPartDto> listByMenuId(long id);
	public MenuDto findById(long id);
	public MenuDto isMenu(List<ProductDto> pd);
	public void add(MenuDto menu, List<MenuPartDto> menuparts);
}
