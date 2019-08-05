package com.example.leaveservice.controller;

import com.example.leaveservice.Exception.*;
import com.example.leaveservice.Model.Leave;
import com.example.leaveservice.Model.Employee;
import com.example.leaveservice.Model.RemLeaves;
import com.example.leaveservice.controller.RemLeavesController;
import com.example.leaveservice.repository.LeaveRepository;
import com.example.leaveservice.repository.RemLeavesRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/leave")
public class LeaveController {
    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private
    RemLeavesRepository remLeavesRepository;

    @Autowired
    private RemLeavesController remLeavesController;

    @GetMapping("")
    public List<Leave> getAllUsersLeave() {
        return leaveRepository.findAll();
    }

    @GetMapping("/{leaveId}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable(value = "leaveId") long leaveId)
            throws ResourceNotFoundException {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + leaveId));
        return ResponseEntity.ok().body(leave);
    }

    @PostMapping("")
    public @ResponseBody
    String createLeave(@Valid @RequestBody Leave leave) throws ResourceNotFoundException {

        Date d1 = leave.getStartingDate();
        Date d2 = leave.getEndingDate();
        long d = d2.getTime() - d1.getTime();
        long diff = TimeUnit.DAYS.convert(d, TimeUnit.MILLISECONDS);


        if(checkManagerId(leave.getEmployeeId(), leave.getManagerId()) && checkDate(leave.getStartingDate(), leave.getEndingDate()) && remLeavesController.checkRemLeaves(leave.getEmployeeId(), diff, leave.getLeaveType()) ) {
            leaveRepository.save(leave);
            return "success";
        }
        else {
            return "Error";
        }
    }


    @PutMapping("/{employeeId}")
    public @ResponseBody
    void updateLeave(@PathVariable(value = "employeeId") long employeeId,
                             @Valid @RequestBody Leave leaveDetails) throws ResourceNotFoundException {
        Leave leave = leaveRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));

        leave.setEmployeeId(leaveDetails.getEmployeeId());
        leave.setManagerId(leaveDetails.getManagerId());
        leave.setLeaveType(leaveDetails.getLeaveType());
        leave.setStartingDate(leaveDetails.getStartingDate());
        leave.setEndingDate(leaveDetails.getEndingDate());
        leave.setComment(leaveDetails.getComment());
        leave.setStatus(leaveDetails.getStatus());

        final Leave updatedLeave = leaveRepository.save(leave);
        ResponseEntity.ok(updatedLeave);

//        return leaveRepository.save(leaveDetails);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteLeave(@PathVariable(value = "employeeId") long employeeId)
            throws ResourceNotFoundException {
        Leave leave = leaveRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));

        leaveRepository.delete(leave);
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
     catch (Exception e) {
         return false;
     }
    }

    private static boolean checkDate(Date from_date, Date to_date) {

        if(from_date.compareTo(to_date) > 0){
            return false;
        }
        else {
            return true;
        }
    }


    @PutMapping("/{employeeId}/{status}/{type}/{noOfDays}")
    public @ResponseBody
    String approveLeave(@PathVariable(value = "employeeId") long employeeId, @PathVariable(value = "status") String status, @PathVariable(value = "type") String type , @PathVariable(value = "noOfDays") long noOfDays,
                     @Valid @RequestBody Leave leaveDetails) throws ResourceNotFoundException {
        Leave leave = leaveRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        leave.setEmployeeId(leaveDetails.getEmployeeId());
        leave.setManagerId(leaveDetails.getManagerId());
        leave.setLeaveType(leaveDetails.getLeaveType());
        leave.setStartingDate(leaveDetails.getStartingDate());
        leave.setEndingDate(leaveDetails.getEndingDate());
        leave.setComment(leaveDetails.getComment());

        if(status.equals("approved")){
            leave.setStatus("approved");

            remLeavesController.changeRemLeaves(employeeId, type, noOfDays);

            String st = deleteLeave(employeeId);
            return "Leave Approved & " + st + " from Database";
        }
        else if(status.equals("denied")){
            leave.setStatus("denied");
            String st = deleteLeave(employeeId);
            return "Leave Denied & " + st + " from Database";
        }
        else {
            return "Error";
        }

//        final Leave updatedLeave = leaveRepository.save(leave);
//        ResponseEntity.ok(updatedLeave);

//        return "success";
    }



}