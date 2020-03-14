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
            value = "SELECT * FROM employee AS e " +
                    "JOIN employee AS e1 ON e.boss_id = e1.id " +
                    "WHERE e.salary > e1.salary ;",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE salary IN " +
                    "(SELECT max(salary) AS salary " +
                    "From Employee " +
                    "GROUP BY department_id)" +
                    "ORDER BY salary DESC;",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "SELECT * FROM employee " +
                    "WHERE boss_id  is null;",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
