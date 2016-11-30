package jason.emeric.app_bar.service;


import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.InitialContext;


@Stateless
public class MailService implements IMailService{
	
	
	@Override
	public void sendMail(String toMail,String toName, String subject,String text, File f){
		
		
		try{
			
			 InitialContext ctx = new InitialContext();  
		        Session session =  
		            (Session) ctx.lookup("mail/test");  
		        // Or by injection.  
		        //@Resource(name = "mail/<name>")  
		        //private Session session;  
		  
		        // Create email and headers.  
		        Message msg = new MimeMessage(session);  
		        msg.setSubject(subject);
		        msg.setRecipient(RecipientType.TO,  
		                         new InternetAddress(  
		                         toMail,  
		                         toName));  
		                         
		       /* msg.setRecipient(RecipientType.CC,  
		                         new InternetAddress(  
		                         "michelle@email.com",  
		                         "Michelle"));  */
		        msg.setFrom(new InternetAddress(  
		                    "auptitgege@gmail.com",  
		                    "Le bar de l'esstin"));  
		        //Address replyToList[] = { new InternetAddress("geraldinegil@hotmail.com") };
		        //msg.setReplyTo(replyToList);
		        // Body text.  
		        BodyPart messageBodyPart = new MimeBodyPart();  
		        messageBodyPart.setText(text);  
		  
		        // Multipart message.  
		        Multipart multipart = new MimeMultipart();  
		        multipart.addBodyPart(messageBodyPart);  
		  
		        // Attachment file from string.  
		        if (f!=null){
		        messageBodyPart = new MimeBodyPart();  
		        messageBodyPart.setFileName(f.getName()); 
		        DataSource source = new FileDataSource(f);
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        multipart.addBodyPart(messageBodyPart); 
		        }
		  
		        // Attachment file from file.  
		       /* messageBodyPart = new MimeBodyPart();  
		        messageBodyPart.setFileName("README2.txt");  
		        DataSource src = new FileDataSource("file.txt");  
		        messageBodyPart.setDataHandler(new DataHandler(src));  
		        multipart.addBodyPart(messageBodyPart);  
		  
		        // Attachment file from byte array.  
		        messageBodyPart = new MimeBodyPart();  
		        messageBodyPart.setFileName("README3.txt");  
		        src = new ByteArrayDataSource(  
		            "file 3 content".getBytes(),  
		            "text/plain");  
		        messageBodyPart.setDataHandler(new DataHandler(src));  
		        multipart.addBodyPart(messageBodyPart);  */
		  
		        // Add multipart message to email.  
		        msg.setContent(multipart);  
		        
		        // Send email.  
		        Transport.send(msg);  
		        System.out.println("mail envoyé");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
	}
}
	
