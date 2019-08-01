package com.example.employeeservice.service;

import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMailService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public  void sendMail(List<String[]> recepient)//throws Exception
    {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String myAccountEmail = "enjoydeepak1437@gmail.com";
            String password = "deepak@12345";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });

            for(String[] str:recepient){
                Message message = prepareMessage(session, myAccountEmail, str);
                Transport.send(message);
                System.out.println("Message send");}

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public Message prepareMessage(Session session,String myAccountEmail,String[] recepient )
    {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient[2]));
            message.setSubject("HRMS portal Login");
            message.setText("Employee Id : "+recepient[0]+"\nPassword : "+recepient[1]+"\n"+"http://localhost:8080/login");
            return message;
        }catch (Exception ex)
        {
            System.out.println("exe1");
            Logger.getLogger(SendMailService.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }

}
