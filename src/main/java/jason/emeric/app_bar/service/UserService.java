package jason.emeric.app_bar.service;

import jason.emeric.app_bar.repository.UserEntity;
import jason.emeric.app_bar.service.model.UserDto;

public class UserService implements IUserService {
	
	public void storeUser(UserDto u, String password){
		// verifier que le mot de passe est suffisamment long
		if (password.length()<10) {
			//throw new PasswordTooShortException();
		}
		// verifier qu'il y a au moins une lettre et un chiffre dans le mot de passe
		
		// hasher mot passe
		
		// controler que le user n'existe pas
		
		// 
		UserEntity ue = new UserEntity();
		ue.setNom(u.getNom());
		//ue.setPassword(hash(password));
		//userRepository.storeUser(ue);
	}

}
