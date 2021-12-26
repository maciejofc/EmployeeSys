package pl.maciejowsky.employeemanagement.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
//All the methods of CrudRepository are annotated with @Transactional
// in implementation class by default at runtime

    //JPQL ?1 is first parameter in method
    // Employee is name from @Entity annotation
    @Query("SELECT e FROM Employee e left join" +
            " fetch e.titles WHERE e.email = ?1")
    public Employee findEmployeeAndInfoByEmail(String email);

//    // native query for inserting and updatuing
//    @Modifying
//    @Transactional
//    @Query(
//            value="update employee set first_name=?1 where email=?2",
//            nativeQuery = true
//    )
//    int updateEmployeeNameByEmailId(String firstName,String emailId);

//    @Query("Select DISTINCT e From Employee e" +
//    " left join fetch e.titles")
    @Query("select e, t.title From Employee e " +
            "left join fetch e.titles t")
    List<Employee> findAllEmployeesWithInfo();

    @Query("SELECT e FROM Employee e left join" +
            " fetch e.titles WHERE e.id = ?1")
    Employee findEmployeeAndInfoById(Long id);
}
