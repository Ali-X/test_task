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
            value = "SELECT empl.id, empl.name, empl.salary, empl.boss_id, empl.department_id " +
                    "FROM employee empl " +
                    "JOIN employee boss ON empl.boss_id = boss.id " +
                    "WHERE boss.salary < empl.salary;",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "SELECT empl.id, empl.name, empl.salary, empl.boss_id, empl.department_id " +
                    "FROM (SELECT *, DENSE_RANK() OVER (PARTITION BY department_id ORDER BY salary DESC) " +
                    "AS rank FROM employee) empl WHERE empl.rank =1;",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "SELECT * FROM employee WHERE boss_id IS null;",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
