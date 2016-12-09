package jason.emeric.app_bar;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.ws.rs.Path;

import jason.emeric.app_bar.Service.IUserService;



@Path("/users")
@ApplicationScoped
public class UserResource {

	@Inject
	private IUserService userService;
	
	
}
