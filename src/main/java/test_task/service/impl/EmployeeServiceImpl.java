package test_task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.DepartmentDao;
import test_task.dao.EmployeeDao;
import test_task.model.Employee;
import test_task.service.EmployeeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
//
    @Override
    public List<Employee> findAllWithoutBoss() {
        return employeeDao.findAllWithoutBoss();
    }

    @Override
    public Long fireEmployee(String name) {
        Iterable<Employee> employees = employeeDao.findAll();

        //TODO Implement method using Collection
        // ---write your code here
        Iterator<Employee> iterator = employees.iterator();
        while(iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getName().equals(name)) {
                iterator.remove();
                employeeDao.delete(employee);
                employeeDao.saveAll(employees);
                return employee.getId();
            }
        }
        return 0L;
    }

    @Override
    public Long changeSalary(String name, BigDecimal salary) {
        Long employeeId = 0L;
        Iterable<Employee> employees = employeeDao.findAll();
        //TODO Implement method using Collection
        // ---write your code here
        List<Employee> employeeList = new ArrayList<>();
        employees.forEach(employeeList::add);
        for (Employee employee : employeeList) {
            if (employee.getName().equals(name)) {
                employee.setSalary(salary);
                employeeId = employee.getId();
            }
        }
        employeeDao.saveAll(employeeList);
        return employeeId;
    }

    @Override
    public Long hireEmployee(Employee employee) {
        //TODO Implement method using Collection and DAO
        // ---write your code here
        departmentDao.save(employee.getDepartment());
        return employeeDao.save(employee).getId();
    }
}
