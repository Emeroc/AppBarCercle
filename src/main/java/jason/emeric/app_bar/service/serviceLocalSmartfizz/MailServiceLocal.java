package fr.esstin.baresstin.service;

import java.io.File;

import javax.ejb.Local;

@Local
public interface MailServiceLocal {

	public void sendMail(String toMail,String toName, String subject,String text,File f);
}
