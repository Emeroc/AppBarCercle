package jason.emeric.app_bar.repository;

import java.util.List;

public interface IUserRepository {

	public void save(UserEntity c);
	
	public UserEntity findById(long id);
	
	public UserEntity findByLogin(String login);
	
	public List<UserEntity> list();

	public void delete(UserEntity existingUserEntity);
	
	public void addBalance(UserEntity c, BalanceHistory b);
	
	public List<BalanceHistory> listHistory(long UserEntityId, int start);
	
	public List<Year> listYear();
	
	public Year findYearById(long id);

}
