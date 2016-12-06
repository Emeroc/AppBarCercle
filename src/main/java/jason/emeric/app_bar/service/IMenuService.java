package jason.emeric.app_bar.service;


import java.util.List;

import javax.ejb.Local;

import jason.emeric.app_bar.service.model.MenuDto;
import jason.emeric.app_bar.service.model.MenuPartDto;
import jason.emeric.app_bar.service.model.ProductDto;



@Local
public interface IMenuService {
	
	public void update(MenuDto m,List<MenuPartDto> menuParts);
	public void delete(long id);
	public List<MenuDto> list();
	public List<MenuPartDto> listByMenuId(long id);
	public MenuDto findById(long id);
	public MenuDto isMenu(List<ProductDto> pd);
	public void add(MenuDto menu, List<MenuPartDto> menuparts);
}
