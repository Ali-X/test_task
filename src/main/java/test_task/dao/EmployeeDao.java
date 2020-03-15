package test_task.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    //TODO Get a list of employees receiving a salary greater than that of the boss
    @Query(value = "SELECT * FROM employees e JOIN employees e2 ON "
            + "e.employee_boss_id = e2.id WHERE e2.salary < e.salary;", nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(value = "select * from list.employees where salary in (select max(salary)"
            + " from list.employees group by department_id);", nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(value = "SELECT * FROM list.employees e JOIN list.employees b "
            + "ON e.employee_boss_id = b.id WHERE e.department_id != b.department_id\n"
            + "OR b.employee_boss_id is NULL;", nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
