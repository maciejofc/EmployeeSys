package pl.maciejowsky.employeemanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dto.EmployeeDTO;
import pl.maciejowsky.employeemanagement.dto.EmployeeWithDetailsDTO;

import pl.maciejowsky.employeemanagement.dto.EmployeeWithDetailsMapper;
import pl.maciejowsky.employeemanagement.manager.EmployeeManager;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//Every method returns a domain object instead of a view
//ResponseBody is not required
// Response body - every request handling method of the controller class automatically serializes return objects into HttpResponse
// object returned -> JSON.
@RequestMapping("/api/employees")
//Request mapping tells that the resources are available at ceratain endpoint
public class EmployeeApi {

    private EmployeeManager employeeManager;

    private EmployeeWithDetailsMapper mapper;

    public EmployeeApi(EmployeeManager employeeManager, EmployeeWithDetailsMapper mapper) {
        this.employeeManager = employeeManager;
        this.mapper = mapper;
    }

    @Autowired



    @GetMapping("/all")
    public ResponseEntity<List<EmployeeWithDetailsDTO>> getAllEmployees() {
        List<Employee> employees= employeeManager.getAllEmployeesWithInfo();
        List<EmployeeWithDetailsDTO> employeeWithDetailsDTOS = employees.stream().map(e -> mapper.toDto(e)).collect(Collectors.toList());
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "List of all Employees");
        //one method by using static method from ResponseEntity
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employeeWithDetailsDTOS);

    }

    @GetMapping()
    public ResponseEntity<EmployeeWithDetailsDTO> getEmployeeByEmail(@RequestParam(required = true) String email) {
        Employee employee = employeeManager.findByEmail(email);
        System.out.println(employee);
        EmployeeWithDetailsDTO employeeWithDetailsDTO = mapper.toDto(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Result of single employee");
        //second method by using new ResponseEntity
        //return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employeeWithDetailsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeWithDetailsDTO> getEmployeeById(@PathVariable(required = true) Long id) {
        Employee employee = employeeManager.findById(id);
        EmployeeWithDetailsDTO employeeWithDetailsDTO = mapper.toDto(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Result of single employee");
        //second method by using new ResponseEntity
        //return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employeeWithDetailsDTO);
    }

    @PostMapping
    //post adding elements
    //request body maps the HttpRequestBody to domain object, JSON -> Java type
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeManager.saveEmployee(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Adding one book");
        return ResponseEntity.status(HttpStatus.OK).headers(header).build();
        // or without header and body
        // return ResponseEntity.ok().build();

    }

    @PutMapping
    //PatchMapping only updating one field
    //overriding elements - updating
    //update only first,last namem titles, email, salary
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {

        Employee employeeEdited = employeeManager.editEmployee(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Updating employee");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employeeEdited);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeManager.deleteById(id);
        return ResponseEntity.ok().build();

    }

}
