package jason.emeric.app_bar.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jason.emeric.app_bar.Entity.UserEntity;

@ApplicationScoped
public class UserRepository implements IUserRepository {

	

		@PersistenceContext
		private EntityManager mgr;
		
		@Override
		public UserEntity findUserById() {
			return new UserEntity();
		}
}
