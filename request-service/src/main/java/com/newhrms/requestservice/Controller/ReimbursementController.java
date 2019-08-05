package com.newhrms.requestservice.Controller;

import com.google.gson.Gson;
import com.newhrms.requestservice.Exception.ResourceNotFoundException;
import com.newhrms.requestservice.Model.Reimbursement;
import com.newhrms.requestservice.Repository.ReimbursementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.newhrms.requestservice.Model.Employee;

import javax.validation.Valid;
import java.util.List;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/reimbursement")
public class ReimbursementController {

    @Autowired
    private ReimbursementRepository reimbursementRepository;

    @GetMapping("")
    public List<Reimbursement> getAllUsersReimbursement() {
        return reimbursementRepository.findAll();
    }

    @GetMapping("/{reimbId}")
    public ResponseEntity<Reimbursement> getUserById(@PathVariable(value = "reimbId") long reimbId)
            throws ResourceNotFoundException {
        Reimbursement reimbursement = reimbursementRepository.findById(reimbId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + reimbId));
        return ResponseEntity.ok().body(reimbursement);
    }

    @PostMapping("")
//    @RequestMapping(value = "/users", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    String createUser(@Valid @RequestBody Reimbursement reimbursement) {


        if(checkManagerId(reimbursement.getEmployeeId(), reimbursement.getManagerId()) && checkAmount(reimbursement.getReimbAmount())){
            reimbursementRepository.save(reimbursement);
            return "success";
        }
        else {
            return "Error";
        }

    }

    @PutMapping("/{reimbId}")
    public @ResponseBody
    Reimbursement updateReimbursement(@PathVariable(value = "reimbId") long reimbId,
                             @Valid @RequestBody Reimbursement reimbDetails) throws ResourceNotFoundException {
        Reimbursement reimbursement = reimbursementRepository.findById(reimbId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + reimbId));

        return reimbursementRepository.save(reimbDetails);
    }

    @DeleteMapping("/{reimbId}")
    public String deleteReimbursement(@PathVariable(value = "reimbId") long reimbId)
            throws ResourceNotFoundException {
        Reimbursement reimbursement = reimbursementRepository.findById(reimbId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + reimbId));

        reimbursementRepository.delete(reimbursement);
        return "deleted";
    }

    private static boolean checkManagerId(long empid, long mgrid){

     try {
         final String uri = "http://localhost:9090/api/user-service/api/employee" + empid;
         RestTemplate restTemplate = new RestTemplate();
         String result = restTemplate.getForObject(uri, String.class);

         Gson gson = new Gson();
         Employee emp = gson.fromJson(result, Employee.class);

         if (mgrid == emp.getManagerId()) {
             return true;
         } else {
             return false;
         }
     }
     catch (Exception e){
         return false;
     }
    }


    private static boolean checkAmount(Double amt) {

        if(amt < 0){
            return false;
        }
        else {
            return true;
        }
    }

}