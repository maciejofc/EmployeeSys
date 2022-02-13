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
    @Query("select e from Employee e")
    List<Employee> findAllEmployeesWithInfo(Pageable page);

    @Query("select e from Employee e " +
            "left join fetch e.titles t" +
            " left join fetch e.departments where e.email = ?1")
    Optional<Employee> findEmployeeAndInfoByEmail(String email);

    @Query("select e from Employee e" +
            " left join fetch e.titles t " +
            " left join fetch e.departments where e.id = ?1")
    Optional<Employee> findEmployeeAndInfoById(Long id);

    @Modifying
    @Query(value =
            "insert into titles (name, employee_id) values (:name, :employee_id)"
            , nativeQuery = true)
    void addTitle(@Param("name") String titleName, @Param("employee_id") Long empNo);

    @Modifying
    @Query(value =
            "delete from titles where title_no=:title_no"
            , nativeQuery = true)
        //SOMETHING TO DO!!!!!!!
    void deleteTitle(@Param("title_no") Long titleNo);



}
