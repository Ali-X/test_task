package test_task.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test_task.dao.EmployeeDao;
import test_task.model.Department;
import test_task.model.Employee;
import test_task.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

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
        Long id = null;

        Iterator iterator = employees.iterator();

        while (iterator.hasNext()) {
            Employee employee = (Employee) iterator.next();
            if (employee.getName().equalsIgnoreCase(name)) {
                id = employee.getId();
                employeeDao.delete(employee);
                break;
            }
        }
        return id;
    }

    @Override
    public Long changeSalary(String name, BigDecimal newSalary) {
        Iterable<Employee> employees = employeeDao.findAll();

        Employee employee = StreamSupport.stream(employees.spliterator(), true)
                .filter(x -> x.getName().equalsIgnoreCase(name))
                .findFirst()
                .get();
        employee.setSalary(newSalary);

        employeeDao.saveAll(employees);
        return employee.getId();
    }

    @Override
    public Long hireEmployee(Employee employee, Optional<Department> department) {
        employee.setDepartment(department.get());
        employeeDao.save(employee);

        return employee.getId();
    }
}
