package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maciejowsky.employeemanagement.dao.DepartmentRepository;
import pl.maciejowsky.employeemanagement.dao.entity.Department;

import java.util.List;

@Service
public class DepartmentManager {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAll(){
        return departmentRepository.findAllDepartmentsWithEmployee();
    }

}
