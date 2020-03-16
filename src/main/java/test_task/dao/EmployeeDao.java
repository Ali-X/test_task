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
            value = "SELECT e.* FROM employee e JOIN employee b ON e.boss_id=b.id "
                    + "WHERE e.salary > b.salary;",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "SELECT e.* FROM employee e WHERE (e.department_id, e.salary) IN " +
                    "(SELECT department_id, MAX(salary) FROM employee GROUP BY department_id);",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "SELECT e.* FROM employee e JOIN employee b ON e.boss_id=b.id "
                    + "WHERE e.department_id != b.department_id;",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
