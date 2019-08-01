package com.example.employeeservice.employeeservice.service;

import com.example.employeeservice.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.io.IOException;
@Service
public class EmployeeDetailsService {
    @Autowired
    EmployeeRepository employeeRepository ;
//    String line="";

    private SendMailService sendMailService;
    public void saveEmployeeDetails()
    {
        sendMailService=new SendMailService();


//            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/employeeDetails.csv"));
//            int count=0;
//            while((line=br.readLine())!=null)
//            {
//                if(count==0) {
//                    count++;
//                    continue;
//                }
//                String[] data=line.split(",");
//                System.out.println(data[0]+" "+data[1]+" "+data[2]+" "+data[3]);
//                User user= new User();
////                if(userRepository.findById(data[1]).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + data[1]))==null)
////                {
//                sendMailService.sendMail(data);
//                user.setUserId(Long.parseLong(data[0]));
//                user.setUsername(data[1]);
//                user.setPassword(data[2]);
//                user.setEmailId(data[3]);
//                userRepository.save(user);
//                // }
//            String[] recipient = new String[10];
//            recipient[0] = "shalvak.mittal12@gmail.com";
//            recipient[1] = "shalvakm@gmail.com";
//            sendMailService.sendMail(recipient);




    }
}