package jason.emeric.app_bar.repository;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements IUserRepository{


	@Override
	public void save(UserEntity c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserEntity findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findByLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserEntity> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(UserEntity existingUserEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBalance(UserEntity c, BalanceHistory b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BalanceHistory> listHistory(long UserEntityId, int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Year> listYear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Year findYearById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	 
}