package io.hrms.employeedetailsservice.Repository;

import io.hrms.employeedetailsservice.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByemployeeId(long employeeId);
}
