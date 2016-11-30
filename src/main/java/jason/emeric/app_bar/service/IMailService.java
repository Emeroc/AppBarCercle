package jason.emeric.app_bar.service;

import java.io.File;

import javax.ejb.Local;

@Local
public interface IMailService {

	public void sendMail(String toMail,String toName, String subject,String text,File f);
}
