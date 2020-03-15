package test_task.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import test_task.model.Department;
import test_task.model.Employee;

public interface EmployeeService {

    List<Employee> findAllBySalaryGreaterThatBoss();

    List<Employee> findAllByMaxSalary();

    List<Employee> findAllWithoutBoss();

    Long fireEmployee(String name);

    Long changeSalary(String name, BigDecimal newSalary);

    Long hireEmployee(Employee employee, Optional<Department> department);
}
