package pl.maciejowsky.employeemanagement.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.maciejowsky.employeemanagement.dao.entity.Department;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    @Query("select d from Department d " +
            "left join fetch d.employees")
    List<Department> findAllDepartmentsWithEmployee();

    @Query("select d from Department d " +
            "left join fetch d.employees where d.name=:departmentName")
    Optional<Department> findDepartmentByName(@Param("departmentName") String name);

    @Modifying
    @Query(value = "insert into employees_departments (emp_no, dept_no) values (:employeeId, :departmentId)",
            nativeQuery = true)
    void saveEmployeeToDepartment(@Param("employeeId") Long employeeId, @Param("departmentId") Long departmentId);

    @Query(value = "select emp_no from employees_departments where dept_no = :departmentId and emp_no = :employeeId"
            , nativeQuery = true)
    Long checkIfThereIsAlreadyEmployeeInDepartment(@Param("departmentId") Long departmentId, @Param("employeeId") Long employeeId);


    @Modifying
    @Query("delete from Department d where d.id=:departmentId")
    void deleteById(@Param("departmentId") Long departmentId);

    @Modifying
    @Query(value = "delete from employees_departments where dept_no=:departmentId and emp_no=:employeeId",
            nativeQuery = true)
    void deleteEmployeeById(@Param("departmentId") Long departmentId, @Param("employeeId") Long employeeId);

}
