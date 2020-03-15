package test_task.service;

import java.util.List;
import java.util.Optional;
import test_task.model.Department;

public interface DepartmentService {

    void save(Department department);

    List<Long> findAllByDepartmentDoesntExceedThreePeople();

    List<Long> findAllByMaxTotalSalary();

    Optional<Department> getDepartmentByName(String name);
}
