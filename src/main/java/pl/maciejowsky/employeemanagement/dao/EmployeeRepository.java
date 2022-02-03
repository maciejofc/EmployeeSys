package pl.maciejowsky.employeemanagement.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;


import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
//All the methods of CrudRepository are annotated with @Transactional
// in implementation class by default at runtime

    @Query("SELECT e FROM Employee e left join" +
            " fetch e.titles WHERE e.email = ?1")
     Optional<Employee> findEmployeeAndInfoByEmail(String email);


    @Query("select e from Employee e")
    List<Employee> findAllEmployeesWithInfo();

    @Modifying
    @Query(value =
            "insert into titles (name, emp_no_foreign_key) values (:name, :emp_no_foreign_key)"
            , nativeQuery = true)
    void addTitle(@Param("name") String titleName, @Param("emp_no_foreign_key") Long empNo);

    @Modifying
    @Query(value =
            "delete from titles where title_no=:title_no"
            , nativeQuery = true)
    void deleteTitle(@Param("title_no") Long empNo);
    @Query("select e from Employee e where e.id = ?1")
    Optional<Employee> findEmployeeAndInfoById(Long id);


}
