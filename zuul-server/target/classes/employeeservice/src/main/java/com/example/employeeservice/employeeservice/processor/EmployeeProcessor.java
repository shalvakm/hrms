package com.example.employeeservice.employeeservice.processor;

import com.example.employeeservice.employeeservice.model.Employee;
import com.example.employeeservice.employeeservice.model.EmployeeDTO;
import org.springframework.batch.item.ItemProcessor;

public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO process(final Employee employee) throws Exception {
        System.out.println("Transforming Employee(s) to EmployeeDTO(s)..");
        final EmployeeDTO empployeeDto = new EmployeeDTO(employee.getName(), employee.getEmpid(),
                 employee.getDesignation(),employee.getEmailid(),employee.getPassword(),employee.getManagerid()
        ,employee.getSalary());

        return empployeeDto;
    }

}
