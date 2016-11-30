package jason.emeric.app_bar.service;


import java.util.List;

import javax.ejb.Local;

import jason.emeric.app_bar.service.model.AdminDto;


@Local
public interface IAdminService {
	
	public void add(AdminDto a);
	public void update (long id);
	public void delete (long id);
	public List<AdminDto> list();
	public AdminDto findById(long id);
	public AdminDto findByLogin(String login);
	public void reactivate(long longValue);

}
