package jason.emeric.app_bar.service;


//import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
//import javax.ejb.Schedule;
import javax.ejb.Singleton;

import jason.emeric.app_bar.service.model.UserDto;


@Singleton
public class CronService {

	@EJB
	private IUserService clientService;

	@EJB
	private IMailService mailService;

	@Schedules(

	@Schedule(dayOfWeek = "Mon-Fri", hour = "23", minute = "0", persistent = false))
	public void backupUser() {
		try {
			List<UserDto> l = clientService.list();
			String s = new String();
			for (UserDto c : l)
				s += c.getName() + ";" + c.getFirstname() + ";" + c.getLogin()
						+ ";" + c.getBalance() + ";\n";
			SimpleDateFormat formater = null;
			Date aujourdhui = new Date();
			formater = new SimpleDateFormat("dd-MM-yy");
			mailService.sendMail("auptitgege@gmail.com", "gege",
					"Sauvegarde Crédit Compte " + formater.format(aujourdhui),
					s, null);
		} catch (Exception e) {

		}
	}

/*
	@Schedules(

	@Schedule(dayOfWeek = "Sat",hour = "5", minute = "0", persistent = false))
	public void backupDatabase2() {
		try {
			
			SimpleDateFormat formater = null;
			Date aujourdhui = new Date();
			formater = new SimpleDateFormat("dd-MM-yy");
			File f = new File("/home/glassfish/backup/backup-production.sql.gz");
			if (f != null)
				mailService
						.sendMail(
								"auptitgege@gmail.com",
								"gege",
								"Sauvegarde Base de Données "
										+ formater.format(aujourdhui),
								"Ceci est la sauvegarde hebdomadaire et complète de la base de données",
								f);
		} catch (Exception e) {

		}
	}
	*/

}
