package jason.emeric.app_bar.repository;


import java.util.List;



public interface IMenuRepository {
	
	public void add(MenuEntity  m);
	
	public MenuEntity findById(long id);
	
	public List<MenuEntity> list();
	
	public void update(MenuEntity m);
	
	public void delete(MenuEntity m);
	
	public MenuPartEntity findMenuPartById(long id);
	
	public List<MenuPartEntity> listMenuPart(long id);

}
