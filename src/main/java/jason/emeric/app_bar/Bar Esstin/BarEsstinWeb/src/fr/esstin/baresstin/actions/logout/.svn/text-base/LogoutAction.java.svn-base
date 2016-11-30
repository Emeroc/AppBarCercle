package fr.esstin.baresstin.actions.logout;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;

public class LogoutAction implements SessionAware{
	
	private Map<String, Object> session;
	
	@SuppressWarnings("rawtypes")
	@Action(value="logout", results={
			@Result(name="success", type="redirect", location="https://auth.univ-lorraine.fr/logout"),
		})
	public String execute()
	{
		if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
		try {
	        ((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
	    } catch (IllegalStateException e) {
	      e.printStackTrace();
	    }
		}
		return "success";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

}
