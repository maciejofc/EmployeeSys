package pl.maciejowsky.employeemanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.dto.DepartmentWithEmployeesDTO;
import pl.maciejowsky.employeemanagement.dto.DepartmentWithEmployeesMapper;
import pl.maciejowsky.employeemanagement.manager.DepartmentManager;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class DepartmentApi {
    private DepartmentManager departmentManager;
    private DepartmentWithEmployeesMapper mapper;


    @Autowired
    public DepartmentApi(DepartmentManager departmentManager, DepartmentWithEmployeesMapper mapper) {
        this.departmentManager = departmentManager;
        this.mapper = mapper;
    }


    @GetMapping("/all")
    public ResponseEntity<List<DepartmentWithEmployeesDTO>> getAllDepartments() {
        List<Department> departments = departmentManager.findAll();
        List<DepartmentWithEmployeesDTO> departmentWithEmployeesDTOS = departments.stream().map(e -> mapper.toDto(e)).collect(Collectors.toList());
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "List of all Departments");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(departmentWithEmployeesDTOS);
    }
}
