package jason.emeric.app_bar.service;



import javax.ejb.Local;

import jason.emeric.app_bar.service.model.UserDto;


@Local
public interface ILDAPService {
	
	public UserDto getInfo(String login);

}
