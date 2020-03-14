package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test_task.dao.DepartmentDao;
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
    @Autowired
    DepartmentDao departmentDao;

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
        Long idEmployee = -1L;
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            if (employee.getName().equals(name)) {
                idEmployee = employee.getId();
                employeeIterator.remove();
                break;
            }
        }
        if (idEmployee != -1) {
            employeeDao.deleteById(idEmployee);
        }
        return idEmployee;
    }

    @Override
    public Long changeSalary(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        // ---write your code here
        Iterator<Employee> employeeIterator = employees.iterator();
        Long idEmployee = -1L;
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            if (employee.getName().equals(name)) {
                idEmployee = employee.getId();
                employee.setSalary(new BigDecimal(100));
                break;
            }
        }
        employeeDao.saveAll(employees);
        return idEmployee;
    }

    @Override
    public Long hireEmployee(Employee employee) {
        //TODO Implement method using Collection and DAO
        // ---write your code here
        employee.setName("NEW EMPLOYEE");
        employee.setDepartment(departmentDao.findById(1l).get());
        employee.setSalary(new BigDecimal(0));
        employee.setBoss(employeeDao.findById(2l).get());
        return employeeDao.save(employee).getId();
    }
}
