package com.newhrms.userservice.Controller;


import com.newhrms.userservice.Exception.ResourceNotFoundException;
import com.newhrms.userservice.Models.Employee;
import com.newhrms.userservice.Models.RemLeaves;
import com.newhrms.userservice.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping("")
//	@RequestMapping(value = "/users", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody Employee createEmployee(@Valid @RequestBody Employee employee) {

        String jsonHeader = "{" +
                "\"employeeId\":" + employee.getEmployeeId() +
                "}";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request= new HttpEntity( jsonHeader, httpHeaders );
        String st = restTemplate.postForObject( "http://localhost:9093/api/remleave", request, String.class );
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    void updateEmployee(@PathVariable(value = "id") long employeeId,
                        @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        	employee.setEmailId(employeeDetails.getEmailId());
		employee.setEmployeeName(employeeDetails.getEmployeeName());
		employee.setDesignation(employeeDetails.getDesignation());
		employee.setSalary(employeeDetails.getSalary());
		employee.setManagerId(employeeDetails.getManagerId());
		final Employee updatedEmployee = employeeRepository.save(employee);
//        return employeeRepository.save(employeeDetails);
        ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
        return "deleted";
    }
}
