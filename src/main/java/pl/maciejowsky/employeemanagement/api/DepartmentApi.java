package pl.maciejowsky.employeemanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.dto.*;
import pl.maciejowsky.employeemanagement.manager.DepartmentManager;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class DepartmentApi {
    private DepartmentManager departmentManager;
    private DepartmentWithEmployeesMapper departmentWithEmployeesMapper;
    private DepartmentMapper departmentMapper;
    private EmployeeMapper employeeMapper;

    @Autowired
    public DepartmentApi(DepartmentManager departmentManager,
                         DepartmentWithEmployeesMapper departmentWithEmployeesMapper,
                         DepartmentMapper departmentMapper,
                         EmployeeMapper employeeMapper) {
        this.departmentManager = departmentManager;
        this.departmentWithEmployeesMapper = departmentWithEmployeesMapper;
        this.departmentMapper = departmentMapper;
        this.employeeMapper = employeeMapper;
    }


    @GetMapping("/all")
    public ResponseEntity<List<DepartmentWithEmployeesDTO>> getAllDepartments() {
        List<Department> departments = departmentManager.findAllDepartmentsWithEmployee();
        List<DepartmentWithEmployeesDTO> departmentWithEmployeesDTOS = departments.stream().map(e -> departmentWithEmployeesMapper.toDto(e)).collect(Collectors.toList());
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "List of all Departments");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(departmentWithEmployeesDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentWithEmployeesDTO> getDepartmentById(@PathVariable Long id) {
        Department department = departmentManager.findDepartmentById(id);
        DepartmentWithEmployeesDTO departmentWithEmployeesDTO = departmentWithEmployeesMapper.toDto(department);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Single department");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(departmentWithEmployeesDTO);
    }

    @GetMapping()
    public ResponseEntity<DepartmentWithEmployeesDTO> getDepartmentByName(@RequestParam String name) {
        Department department = departmentManager.findDepartmentByName(name);
        DepartmentWithEmployeesDTO departmentWithEmployeesDTO = departmentWithEmployeesMapper.toDto(department);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Single department");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(departmentWithEmployeesDTO);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        Department department = departmentMapper.toModel(departmentDTO);
        departmentManager.saveDepartment(department);
        HttpHeaders header = new HttpHeaders();
        String uriLocation= "/api/departments/"+department.getId();
        header.setLocation(URI.create(uriLocation));
        header.add("Description", "Added one department");
        return ResponseEntity.status(HttpStatus.CREATED).headers(header).build();
    }

    @PostMapping("/{id}/employees")
    public ResponseEntity<Void> addEmployeeToDepartment(@RequestParam Long employeeId, @PathVariable Long id) {
        departmentManager.saveEmployeeToDepartment(employeeId, id);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Aded one employee to department");
        return ResponseEntity.status(HttpStatus.OK).headers(header).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO, Long id) {

        Department editedDepartment = departmentMapper.toModel(departmentDTO);
        departmentManager.editDepartment(editedDepartment, id);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Updated department");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(departmentDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentManager.deleteDepartment(id);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Deleted one department");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(header).build();
    }


    @DeleteMapping("/{id}/employees/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeFromDepartment(@PathVariable Long employeeId, @PathVariable Long id) {
        departmentManager.deleteEmployeeFromDepartment(employeeId, id);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Deleted employee from department");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(header).build();
    }


}
