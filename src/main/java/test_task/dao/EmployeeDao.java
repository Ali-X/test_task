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
                    "FROM employee empl JOIN employee boss ON empl.boss_id = boss.id " +
                    "where empl.salary > boss.salary ",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "SELECT * from employee where salary in (" +
                    "select max(salary) from employee where boss_id is not null group by boss_id)" +
                    "and boss_id is not null",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "SELECT empl.id, empl.name, empl.salary, empl.boss_id, empl.department_id " +
                    "FROM employee empl JOIN employee boss ON empl.boss_id = boss.id " +
                    "where empl.department_id <> boss.department_id",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
