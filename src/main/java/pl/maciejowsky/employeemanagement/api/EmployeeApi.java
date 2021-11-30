package pl.maciejowsky.employeemanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.manager.EmployeeManager;

import java.util.List;

@RestController
//Every method returns a domain object instead of a view
//ResponseBody is not required
// Response body - every request handling method of the controller class automatically serializes return objects into HttpResponse
// object returned -> JSON.
@RequestMapping("/api/employees")
//Request mapping tells that the resources are available at ceratain endpoint
public class EmployeeApi {

    private EmployeeManager employeeManager;

    @Autowired
    public EmployeeApi(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = (List<Employee>) employeeManager.findAll();
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "List of all Employees");
        //one method by using static method from ResponseEntity
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employees);

    }

    @GetMapping()
    public ResponseEntity<Employee> getByEmail(@RequestParam String email) {
        Employee employee = employeeManager.findByEmail(email);
        System.out.println(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Result of single employee");
        //second method by using new ResponseEntity
        //return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employee);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable long id) {
        Employee employee = employeeManager.findById(id);
        System.out.println(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Result of single employee");
        //second method by using new ResponseEntity
        //return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employee);
    }

    @PostMapping
    //post adding elements
    //request body maps the HttpRequestBody to domain object, JSON -> Java type
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeManager.save(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Adding one book");
        return ResponseEntity.status(HttpStatus.OK).headers(header).build();
        // or without header and body
        // return ResponseEntity.ok().build();

    }

    @PutMapping
    //PatchMapping only updating one field
    //overriding elements - updating
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
        employeeManager.save(employee);
        return ResponseEntity.ok().build();


    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@RequestParam int index) {
        employeeManager.deleteById((long) index);
        return ResponseEntity.ok().build();

    }

}
