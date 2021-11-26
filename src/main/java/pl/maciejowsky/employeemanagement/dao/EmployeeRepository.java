package pl.maciejowsky.employeemanagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
//All the methods of CrudRepository are annotated with @Transactional
// in implementation class by default at runtime

}
