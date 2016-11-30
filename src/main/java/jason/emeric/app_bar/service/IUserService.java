package jason.emeric.app_bar.service;

import java.math.BigDecimal;
import java.util.List;

import jason.emeric.app_bar.service.model.UserDto;

public interface IUserService {

	public boolean add(UserDto o);
	
	public List<UserDto> list();
	
	public UserDto findById(long id);
	
	public UserDto findByIdOrCreateGuest(long id, String name);
	
	public UserDto findByLogin(String login)throws UserDoesntExistException;
	
	public void delete(long id);
	
	public void update(UserDto c);
	
	public void addBalance(long cId, BigDecimal price, boolean check, String admin);
	
	public List<BalanceHistoryDto> listHistory(long clientId,int start);
	
	public List<YearDto> listYear();

}
