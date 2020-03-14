package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Override
    public List<Employee> findAllBySalaryGreaterThatBoss() {
        //return new ArrayList<>();
        return employeeDao.findAllWhereSalaryGreaterThatBoss();
    }

    @Override
    public List<Employee> findAllByMaxSalary() {
       // return new ArrayList<>();

         return employeeDao.findAllByMaxSalary();
    }

    @Override
    public List<Employee> findAllWithoutBoss() {
        //return new ArrayList<>();

         return employeeDao.findAllWithoutBoss();
    }

    @Override
    public Long fireEmployee(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        for (Employee employee: employees) {
            if (employee.getName().equals(name)) {
                employeeDao.delete(employee);
                return employee.getId();
            }
        }
        throw new NoSuchElementException("Employee with name - " + name + " not found");
    }

    @Override
    public Long changeSalary(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        for (Employee employee: employees) {
            if (employee.getName().equals(name)) {
                employee.setSalary(BigDecimal.valueOf(8800));
                employeeDao.save(employee);
                return employee.getId();
            }
        }
        throw new NoSuchElementException("Employee with name - " + name + " not found");
    }

    @Override
    public Long hireEmployee(Employee employee) {
        //TODO Implement method using Collection and DAO
        Employee newEmployee = employeeDao.save(employee);
        return newEmployee.getId();
    }
}
