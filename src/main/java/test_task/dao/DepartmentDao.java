package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test_task.model.Department;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {
    //    //TODO Get a list of department IDS where the number of employees doesn't exceed 3 people
    @Query(
            value = "SELECT department.id " +
                    "FROM department " +
                    "JOIN employee " +
                    "ON employee.department_id = department.id " +
                    "GROUP BY department.id " +
                    "HAVING COUNT(*) <= 3;",
            nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employees
    @Query(
            value = "SELECT department_id FROM  employee " +
                    "GROUP BY department_id " +
                    "Having SUM (salary) = (SELECT MAX(sum_salary) " +
                    "FROM (SELECT SUM (salary) sum_salary " +
                    "FROM employee " +
                    "GROUP BY department_id) employee); ",
            nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();


}
