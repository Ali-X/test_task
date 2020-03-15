package test_task.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Department;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {
    //TODO Get a list of department IDS where the number of employees doesn't exceed 3 people
    @Query(value = "SELECT id FROM list.departments where id in \n"
            + "(SELECT department_id FROM list.employees group by department_id "
            + "having count(name) < 4);", nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employees
    @Query(value = "SELECT department_id from list.departments d \n"
            + "join list.employees e on d.id =\n"
            + " e.department_id group by department_id having sum(salary) = (\n"
            + "SELECT * FROM (\n"
            + "SELECT sum(salary) FROM list.employees\n"
            + "group by department_id order by sum(salary) desc limit 1) \n"
            + "as t);", nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();

    Optional<Department> getDepartmentByName(String name);
}
