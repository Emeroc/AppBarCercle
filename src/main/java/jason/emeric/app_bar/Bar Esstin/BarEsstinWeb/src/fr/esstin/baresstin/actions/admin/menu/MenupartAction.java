package fr.esstin.baresstin.actions.admin.menu;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MenupartAction  extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long count;
	private String name;

	public String execute() {
		try{
			count = new Long(((String[]) ActionContext.getContext().getParameters().get("count"))[0]).longValue();
			name = (((String[]) ActionContext.getContext().getParameters().get("name"))[0]);
			}
			catch (NullPointerException e)
			{
				return ERROR;
			}
			return "success";		
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
