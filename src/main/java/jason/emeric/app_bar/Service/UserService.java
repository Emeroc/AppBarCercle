package jason.emeric.app_bar.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import jason.emeric.app_bar.Repository.IUserRepository;

@ApplicationScoped
public class UserService implements IUserService{
	
	@Inject
	private IUserRepository UserRep;
	
	@Override
	public void addUser(){
		
	}
	

}
