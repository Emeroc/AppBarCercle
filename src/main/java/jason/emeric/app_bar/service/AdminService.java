package jason.emeric.app_bar.service;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import jason.emeric.app_bar.repository.AdminEntity;
import jason.emeric.app_bar.repository.IAdminRepository;
import jason.emeric.app_bar.service.model.AdminDto;


@Stateless
public class AdminService implements IAdminService {

	@Inject
	IAdminRepository adminRep;

	@Override
	public void add(AdminDto a) {
		if (!a.getLogin().isEmpty())
		{
			a.setEnable(true);
			adminRep.add(fromDto(a));
		}

	}

	@Override
	public void update(long id) {
		AdminEntity a = adminRep.findById(id);
		if(a==null)
			return;
		if(a.isEnable())
			a.setEnable(false);
		else
			a.setEnable(true);
		adminRep.update(a);

	}

	@Override
	public List<AdminDto> list() {
		return fromEntityList(adminRep.list());
	}

	@Override
	public AdminDto findById(long id) {
		return fromEntity(adminRep.findById(id));
	}

	public AdminEntity fromDto(AdminDto ad) {
		AdminEntity a = new AdminEntity();
		a.setEnable(ad.isEnable());
		a.setLogin(ad.getLogin());
		return a;
	}
	
	public AdminDto fromEntity(AdminEntity a) {
		AdminDto ad = new AdminDto();
		ad.setEnable(a.isEnable());
		ad.setLogin(a.getLogin());
		ad.setId(a.getId());
		return ad;
	}
	
	public List<AdminDto> fromEntityList(List<AdminEntity> la)
	{
		List<AdminDto> lad = new ArrayList<AdminDto>();
		for(AdminEntity a: la)
		{
			lad.add(fromEntity(a));
		}
		return lad;
	}

	@Override
	public AdminDto findByLogin(String login) {
		AdminEntity a = adminRep.findByLogin(login);
		if (a==null)
			return null;
		return fromEntity(a);
	}

	@Override
	public void delete(long id) {
		adminRep.delete(id);
		
	}

	@Override
	public void reactivate(long longValue) {
		adminRep.reactivate(longValue);
		
	}

}
