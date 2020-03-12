package test_task.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> findAllBySalaryGreaterThatBoss() {
        return employeeDao.findAllWhereSalaryGreaterThatBoss();
    }

    @Override
    public List<Employee> findAllByMaxSalary() {
        return employeeDao.findAllByMaxSalary();
    }

    @Override
    public List<Employee> findAllWithoutBoss() {
        return employeeDao.findAllWithoutBoss();
    }

    @Override
    public Long fireEmployee(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        // ---write your code here
        Optional<Employee> optionalEmployee = StreamSupport
                .stream(employees.spliterator(), false)
                .filter(e -> e.getName().equals(name))
                .findFirst();

        Employee employee;
        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
            employeeDao.delete(employee);
            return employee.getId();
        }

        return 0L;
    }

    @Override
    public Long changeSalary(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        // ---write your code here


        Optional<Employee> optionalEmployee = StreamSupport
                .stream(employees.spliterator(), false)
                .filter(e -> e.getName().equals(name))
                .findFirst();

        // Should be the parameter new salary in signature, if we want to change it?
        BigDecimal newSalary = new BigDecimal(50000);
        Employee employee;

        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
            employee.setSalary(newSalary);
            employeeDao.save(employee);
            return employee.getId();
        }

        return 0L;
    }

    @Override
    public Long hireEmployee(Employee employee) {
        //TODO Implement method using Collection and DAO
        // ---write your code here

        Employee newEmployee = employeeDao.save(employee);
        return newEmployee.getId();
    }
}
