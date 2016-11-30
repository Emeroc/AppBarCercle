package fr.esstin.baresstin.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.esstin.baresstin.Admin;
import fr.esstin.baresstin.dao.AdminDao;
import fr.esstin.baresstin.dto.AdminDto;

@Stateless
public class AdminService implements AdminServiceLocal {

	@Inject
	AdminDao adminDao;

	@Override
	public void add(AdminDto a) {
		if (!a.getLogin().isEmpty())
		{
			a.setEnable(true);
			adminDao.add(fromDto(a));
		}

	}

	@Override
	public void update(long id) {
		Admin a = adminDao.findById(id);
		if(a==null)
			return;
		if(a.isEnable())
			a.setEnable(false);
		else
			a.setEnable(true);
		adminDao.update(a);

	}

	@Override
	public List<AdminDto> list() {
		return fromEntityList(adminDao.list());
	}

	@Override
	public AdminDto findById(long id) {
		return fromEntity(adminDao.findById(id));
	}

	public Admin fromDto(AdminDto ad) {
		Admin a = new Admin();
		a.setEnable(ad.isEnable());
		a.setLogin(ad.getLogin());
		return a;
	}
	
	public AdminDto fromEntity(Admin a) {
		AdminDto ad = new AdminDto();
		ad.setEnable(a.isEnable());
		ad.setLogin(a.getLogin());
		ad.setId(a.getId());
		return ad;
	}
	
	public List<AdminDto> fromEntityList(List<Admin> la)
	{
		List<AdminDto> lad = new ArrayList<AdminDto>();
		for(Admin a: la)
		{
			lad.add(fromEntity(a));
		}
		return lad;
	}

	@Override
	public AdminDto findByLogin(String login) {
		Admin a = adminDao.findByLogin(login);
		if (a==null)
			return null;
		return fromEntity(a);
	}

	@Override
	public void delete(long id) {
		adminDao.delete(id);
		
	}

	@Override
	public void reactivate(long longValue) {
		adminDao.reactivate(longValue);
		
	}
}
