package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Department;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {
    @Query(
            value = "select department_id from employee group by department_id having count(id) != 3",
            nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employees
    @Query(
            value = "SELECT sums.department_id FROM (SELECT department_id, SUM(salary) AS ss1 " +
                    "FROM employee GROUP BY department_id) AS sums WHERE ss1 >= (SELECT max(ss2) " +
                    "FROM (SELECT department_id, SUM(salary) AS ss2 FROM employee " +
                    "GROUP BY department_id) AS max_sum);",
            nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();
}
