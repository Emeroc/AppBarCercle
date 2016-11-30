package fr.esstin.baresstin.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.esstin.baresstin.Menu;
import fr.esstin.baresstin.MenuPart;
import fr.esstin.baresstin.Product;
import fr.esstin.baresstin.dao.MenuDao;
import fr.esstin.baresstin.dao.ProductDao;
import fr.esstin.baresstin.dto.MenuDto;
import fr.esstin.baresstin.dto.MenuPartDto;
import fr.esstin.baresstin.dto.ProductDto;
import fr.esstin.baresstin.service.exceptions.ClientDoesntExistException;
import fr.esstin.baresstin.service.exceptions.ProductDoesntExistException;

@Stateless
public class MenuService implements MenuServiceLocal {

	@Inject
	MenuDao menuDao;
	@Inject
	ProductDao productDao;

	@Override
	public void update(MenuDto md, List<MenuPartDto> menuPartsd) {
		Menu m = menuDao.findById(md.getId());
		if (m == null)
			throw new ClientDoesntExistException("Client  doesn't exist");// TODO
		fromDto(md, m);
		// m.getMenuParts().clear();
		m.setMenuParts(fromDtoMenuPart(menuPartsd));
		for (MenuPart mp : m.getMenuParts())
			mp.setMenu(m);
		menuDao.update(m);
	}

	@Override
	public void delete(long id) {
		Menu m = menuDao.findById(id);
		if (m == null)
			throw new ClientDoesntExistException("Client  doesn't exist");// TODO
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

	public MenuDto fromEntity(Menu m) {
		MenuDto md = new MenuDto();
		md.setId(m.getId());
		md.setName(m.getName());
		md.setPrice(m.getPrice());
		return md;
	}

	public void fromDto(MenuDto md, Menu m) {
		m.setName(md.getName());
		m.setPrice(md.getPrice());
	}

	public MenuPartDto fromEntity(MenuPart mp) {
		MenuPartDto mpd = new MenuPartDto();
		mpd.setName(mp.getName());
		mpd.setId(mp.getId());
		List<ProductDto> lProducts = new ArrayList<ProductDto>();
		for (Product entry : mp.getProducts()) {
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

	public void fromDto(MenuPartDto mpd, MenuPart mp) {

		mp.setName(mpd.getName());
		mp.setEnable(true);
		List<Product> lProducts = new ArrayList<Product>();
		if (mpd.getProducts() != null)
			for (ProductDto entry : mpd.getProducts()) {
				Product p = productDao.findById(entry.getId());
				if (p == null)
					throw new ProductDoesntExistException("Product "
							+ entry.getId() + "  doesn't exist");
				lProducts.add(p);
			}
		mp.setProducts(lProducts);
	}

	public List<MenuPartDto> fromEntityMenuPart(List<MenuPart> lst) {
		List<MenuPartDto> results = new ArrayList<MenuPartDto>();
		for (MenuPart ve : lst) {
			results.add(fromEntity(ve));
		}
		return results;
	}

	public List<MenuPart> fromDtoMenuPart(List<MenuPartDto> lst) {
		List<MenuPart> results = new ArrayList<MenuPart>();
		if (lst != null) {
			int i = 0;
			for (MenuPartDto ve : lst) {
				System.out.println("azerty : " + i);
				i++;
				MenuPart mp;
				try {
					mp = menuDao.findMenuPartById(ve.getId());
					if (mp == null)
						mp = new MenuPart();
				} catch (NullPointerException e) {
					System.out.println("outch");
					mp = new MenuPart();
				}
				fromDto(ve, mp);
				results.add(mp);
			}
		}
		return results;
	}

	public List<MenuDto> fromEntityMenuList(List<Menu> lst) {
		List<MenuDto> results = new ArrayList<MenuDto>();
		for (Menu ve : lst) {
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
		Menu m = new Menu();
		System.out.println("sdfsdf : "+menu.getName());
		fromDto(menu, m);
		System.out.println("sdfsdf : "+m.getName());
		m.setMenuParts(fromDtoMenuPart(menuparts));
		for (MenuPart mp : m.getMenuParts())
			mp.setMenu(m);
		menuDao.add(m);
		
	}
}
