package pl.maciejowsky.employeemanagement.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.maciejowsky.employeemanagement.dao.entity.Department;

import java.util.List;

@Repository
public interface DepartmentRepository  extends CrudRepository<Department,Long> {
@Query("SELECT d FROM Department d")
List<Department> findAllDepartmentsWithEmployee();
}
