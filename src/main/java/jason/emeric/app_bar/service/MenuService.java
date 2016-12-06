package jason.emeric.app_bar.service;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import jason.emeric.app_bar.repository.IMenuRepository;
import jason.emeric.app_bar.repository.IProductRepository;
import jason.emeric.app_bar.repository.MenuEntity;
import jason.emeric.app_bar.repository.MenuPartEntity;
import jason.emeric.app_bar.repository.ProductEntity;
import jason.emeric.app_bar.service.exception.ProductDoesntExistException;
import jason.emeric.app_bar.service.exception.UserDoesntExistException;
import jason.emeric.app_bar.service.model.MenuDto;
import jason.emeric.app_bar.service.model.MenuPartDto;
import jason.emeric.app_bar.service.model.ProductDto;



@Stateless
public class MenuService implements IMenuService {

	@Inject
	IMenuRepository menuDao;
	@Inject
	IProductRepository productDao;

	@Override
	public void update(MenuDto md, List<MenuPartDto> menuPartsd) {
		MenuEntity m = menuDao.findById(md.getId());
		if (m == null)
			throw new UserDoesntExistException("Client  doesn't exist");// TODO
		fromDto(md, m);
		// m.getMenuParts().clear();
		m.setMenuParts(fromDtoMenuPart(menuPartsd));
		for (MenuPartEntity mp : m.getMenuParts())
			mp.setMenu(m);
		menuDao.update(m);
	}

	@Override
	public void delete(long id) {
		MenuEntity m = menuDao.findById(id);
		if (m == null)
			throw new UserDoesntExistException("Client  doesn't exist");// TODO
		menuDao.delete(m);

	}

	@Override
	public List<MenuDto> list() {
		return (fromEntityMenuList(menuDao.list()));
	}

	@Override
	public List<MenuPartDto> listByMenuId(long id) {
		return fromEntityMenuPart(menuDao.listMenuPart(id));
	}

	@Override
	public MenuDto findById(long id) {
		return fromEntity(menuDao.findById(id));
	}

	public MenuDto fromEntity(MenuEntity m) {
		MenuDto md = new MenuDto();
		md.setId(m.getId());
		md.setName(m.getName());
		md.setPrice(m.getPrice());
		return md;
	}

	public void fromDto(MenuDto md, MenuEntity m) {
		m.setName(md.getName());
		m.setPrice(md.getPrice());
	}

	public MenuPartDto fromEntity(MenuPartEntity mp) {
		MenuPartDto mpd = new MenuPartDto();
		mpd.setName(mp.getName());
		mpd.setId(mp.getId());
		List<ProductDto> lProducts = new ArrayList<ProductDto>();
		for (ProductEntity entry : mp.getProducts()) {
			ProductDto pd = new ProductDto();
			pd.setId(entry.getId());
			pd.setName(entry.getName());
			pd.setPrice(entry.getPrice());
			pd.setAvailable(entry.getAvailable());
			pd.setEnable(entry.isEnable());
			lProducts.add(pd);
		}
		mpd.setProducts(lProducts);
		return mpd;
	}

	public void fromDto(MenuPartDto mpd, MenuPartEntity mp) {

		mp.setName(mpd.getName());
		mp.setEnable(true);
		List<ProductEntity> lProducts = new ArrayList<ProductEntity>();
		if (mpd.getProducts() != null)
			for (ProductDto entry : mpd.getProducts()) {
				ProductEntity p = productDao.findById(entry.getId());
				if (p == null)
					throw new ProductDoesntExistException("Product "
							+ entry.getId() + "  doesn't exist");
				lProducts.add(p);
			}
		mp.setProducts(lProducts);
	}

	public List<MenuPartDto> fromEntityMenuPart(List<MenuPartEntity> lst) {
		List<MenuPartDto> results = new ArrayList<MenuPartDto>();
		for (MenuPartEntity ve : lst) {
			results.add(fromEntity(ve));
		}
		return results;
	}

	public List<MenuPartEntity> fromDtoMenuPart(List<MenuPartDto> lst) {
		List<MenuPartEntity> results = new ArrayList<MenuPartEntity>();
		if (lst != null) {
			int i = 0;
			for (MenuPartDto ve : lst) {
				System.out.println("azerty : " + i);
				i++;
				MenuPartEntity mp;
				try {
					mp = menuDao.findMenuPartById(ve.getId());
					if (mp == null)
						mp = new MenuPartEntity();
				} catch (NullPointerException e) {
					System.out.println("outch");
					mp = new MenuPartEntity();
				}
				fromDto(ve, mp);
				results.add(mp);
			}
		}
		return results;
	}

	public List<MenuDto> fromEntityMenuList(List<MenuEntity> lst) {
		List<MenuDto> results = new ArrayList<MenuDto>();
		for (MenuEntity ve : lst) {
			results.add(fromEntity(ve));
		}
		return results;
	}

	@Override
	public MenuDto isMenu(List<ProductDto> pd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(MenuDto menu, List<MenuPartDto> menuparts) {
		MenuEntity m = new MenuEntity();
		System.out.println("sdfsdf : "+menu.getName());
		fromDto(menu, m);
		System.out.println("sdfsdf : "+m.getName());
		m.setMenuParts(fromDtoMenuPart(menuparts));
		for (MenuPartEntity mp : m.getMenuParts())
			mp.setMenu(m);
		menuDao.add(m);
		
	}
}
