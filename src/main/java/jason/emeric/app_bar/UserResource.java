package jason.emeric.app_bar;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import jason.emeric.app_bar.Service.IUserService;



@Path("/users")
@ApplicationScoped
public class UserResource {

	@Inject
	private IUserService userService;
	
	@GET
	public Response getUser() {

		return Response.ok(userService.getUser()).build();
	}
	
	@POST
	public Response postUser(UserDto dto) {
		//userService.storeUser(dto);
		System.out.println(dto.getEmail());
		return Response.ok(userService.getUser()).build();
	}
}
