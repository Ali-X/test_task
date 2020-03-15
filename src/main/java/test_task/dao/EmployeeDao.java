package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Employee;

import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    //TODO Get a list of employees receiving a salary greater than that of the boss
    @Query(
            value = "SELECT e.* FROM employees e\n" +
                    "INNER JOIN employees emp ON e.boss_id = emp.id\n" +
                    "WHERE e.salary > emp.salary AND e.department_id = emp.department_id"
            ,
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "SELECT e.* FROM employees e\n" +
                    "GROUP BY e.id HAVING salary = (SELECT MAX(emp.salary) FROM employees emp\n" +
                    "WHERE emp.department_id = e.department_id)\n",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "SELECT e.* FROM employees e\n" +
                    "WHERE boss_id IS NULL"
            ,
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
