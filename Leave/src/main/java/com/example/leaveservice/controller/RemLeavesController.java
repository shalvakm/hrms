package com.example.leaveservice.controller;

import com.example.leaveservice.Exception.ResourceNotFoundException;
//import com.example.leaveservice.Model.Leave;
import com.example.leaveservice.Model.RemLeaves;
import com.example.leaveservice.repository.RemLeavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/remleave")
public class RemLeavesController {

    @Autowired
    private RemLeavesRepository remLeavesRepository;

    @GetMapping("")
    public List<RemLeaves> getAllRemainingLeaves() {
        return remLeavesRepository.findAll();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<RemLeaves> getRemLeaveById(@PathVariable(value = "employeeId") long employeeId)
            throws ResourceNotFoundException {
        RemLeaves remleave = remLeavesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(remleave);
    }

    @PostMapping("")
    public @ResponseBody
    String createRemLeave(@Valid @RequestBody RemLeaves remleave) {

            remLeavesRepository.save(remleave);
            return "success";
    }

    @PutMapping("approve/{employeeId}/{type}/{change}")
    public @ResponseBody
    void approvedLeave(@PathVariable(value = "employeeId") long employeeId, @PathVariable(value = "type") String type ,@PathVariable(value = "change") long change,
                      @Valid @RequestBody RemLeaves remLeaveDetails) throws ResourceNotFoundException {
        RemLeaves remLeaves = remLeavesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + employeeId));

//        return remLeavesRepository.save(remLeaveDetails);

        remLeaves.setEmployeeId(remLeaveDetails.getEmployeeId());

        if(type.equals("casual"))
        {
            remLeaves.setCasual(remLeaves.getCasual() - change);
        }
        else if(type.equals("sick")){
            remLeaves.setSick(remLeaves.getSick() - change);
        }
        else if(type.equals("unpaid")){
            remLeaves.setUnpaid(remLeaves.getUnpaid() + change);
        }
        else {
            remLeaves.setUnpaid(remLeaves.getUnpaid());
        }
        final RemLeaves updatedRemLeaves = remLeavesRepository.save(remLeaves);
        ResponseEntity.ok(updatedRemLeaves);
    }

    public void changeRemLeaves(long employeeId, String type, long days)throws ResourceNotFoundException {
        RemLeaves remLeaves = remLeavesRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Emp not found for this id :: " + employeeId));


        approvedLeave(employeeId, type, days, remLeaves);

    }



}
