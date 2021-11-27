package pl.maciejowsky.employeemanagement.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
//All the methods of CrudRepository are annotated with @Transactional
// in implementation class by default at runtime

    //JPQL ?1 is first parameter in method
    // Employee is name from @Entity annotation
    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    public Employee findByEmail(String email);
}
