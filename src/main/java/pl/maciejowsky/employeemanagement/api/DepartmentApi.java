package pl.maciejowsky.employeemanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.manager.DepartmentManager;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentApi {
    private DepartmentManager departmentManager;

    @Autowired
    public DepartmentApi(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentManager.findAll();
        HttpHeaders header = new HttpHeaders();
        header.add("Description","List of all Departments");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(departments);
    }
}
