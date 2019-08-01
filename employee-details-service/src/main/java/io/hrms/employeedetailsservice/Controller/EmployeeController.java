package io.hrms.employeedetailsservice.Controller;

import io.hrms.employeedetailsservice.Exception.ResourceNotFoundException;
import io.hrms.employeedetailsservice.Models.Employee;
import io.hrms.employeedetailsservice.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @GetMapping("/employees/{id}")
    public Long sendManagerId(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findByemployeeId(employeeId);
        if (Objects.isNull(employee)) {
            System.out.println("id not found");
            return Long.valueOf(121212l);
        }
        return Long.valueOf(employee.getManagerId());
    }


    @PostMapping("/employees")
    public @ResponseBody
    Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public @ResponseBody
    Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                            @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        return employeeRepository.save(employeeDetails);//ResponseEntity.ok(updatedEmployee);
    }

//    @DeleteMapping("/employees/{id}")
//    public String deleteEmployee(@PathVariable(value = "id") Long employeeId)
//            throws ResourceNotFoundException {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//    }
}