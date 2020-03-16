package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

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
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            if (employee.getName().equals(name)) {
                employeeIterator.remove();
                employeeDao.delete(employee);
                return employee.getId();
            }
        }
        employeeDao.saveAll(employees);
        return 0L;
    }

    @Override
    public Long changeSalary(String name) {
        BigDecimal newSalary = new BigDecimal(5000);
        //I think we need this "newSalary" argument in the method`s signature
        Iterable<Employee> employees = employeeDao.findAll();
        //TODO Implement method using Collection
        // ---write your code here
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                employee.setSalary(newSalary);
                employeeDao.save(employee);
                return employee.getId();
            }
        }
        employeeDao.saveAll(employees);
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
