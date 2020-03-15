package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Department;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {
    //TODO Get a list of department IDS where the number of employeess doesn't exceed 3 people
    @Query(
            value = "SELECT departments.id FROM departments\n" +
                    "INNER JOIN employees e ON departments.id = e.department_id" +
                    " GROUP BY departments.id " +
                    "HAVING COUNT(*) > 3;",
            nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employeess
    @Query(
            value = "SELECT department_id FROM(SELECT department_id, summary, RANK() OVER (ORDER BY summary DESC) sum_rank FROM " +
                    "(SELECT department_id, SUM(salary) AS summary FROM employees GROUP BY department_id) AS tab) as result\n" +
                    "WHERE sum_rank = 1;",
            nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();

//    SELECT  Products.CategoryID, MAX(price)
//    FROM (SELECT Products.CategoryID, SUM(price) AS price FROM Products
//    INNER JOIN Categories ON Categories.CategoryID = Products.CategoryID
//    GROUP BY Products.CategoryID)
//    GROUP BY Products.CategoryID;
}
