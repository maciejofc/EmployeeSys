package pl.maciejowsky.employeemanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;
import pl.maciejowsky.employeemanagement.dto.*;
import pl.maciejowsky.employeemanagement.manager.EmployeeManager;

import java.util.ArrayList;
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
    private TitleMapper titleMapper;
    private EmployeeWithDetailsMapper mapperWithDetails;
    private EmployeeMapper mapper;
    private EmployeeWithTitlesMapper mapperWithTitles;

    @Autowired
    public EmployeeApi(EmployeeManager employeeManager, EmployeeWithDetailsMapper mapperWithDetails,
                       EmployeeMapper mapper, TitleMapper titleMapper,
                       EmployeeWithTitlesMapper mapperWithTitles) {
        this.employeeManager = employeeManager;
        this.mapperWithDetails = mapperWithDetails;
        this.mapper = mapper;
        this.titleMapper = titleMapper;
        this.mapperWithTitles = mapperWithTitles;
    }


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeWithTitlesDTO>> getAllEmployees(@RequestParam(required = false) int page, @RequestParam(name="orders") List<String> sort) {
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            if (sort.get(0).contains(",")) {
                //will sort 2 or more than 2 columns
                for (String sortOrder : sort) {
                    // sortOrder = "column,direction"
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                //sort = ["column","direction"]
                orders.add(new Sort.Order(getSortDirection(sort.get(1)), sort.get(0)));
            }
            Sort definedSort = Sort.by(orders);

            List<Employee> employees = employeeManager.findAllEmployeesWithInfo(page, definedSort);
            List<EmployeeWithTitlesDTO> employeeWithTitlesDTOS = employees.stream().map(e -> mapperWithTitles.toDto(e)).collect(Collectors.toList());
            HttpHeaders header = new HttpHeaders();
            header.add("Description", "List of all sorted employees");
            //one method by using static method from ResponseEntity

            return ResponseEntity.status(HttpStatus.OK).headers(header).body(employeeWithTitlesDTOS);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<EmployeeWithDetailsDTO> getEmployeeByEmail(@RequestParam(required = true) String email) {
        Employee employee = employeeManager.findEmployeeAndInfoByEmail(email);
        System.out.println(employee);
        EmployeeWithDetailsDTO employeeWithDetailsDTO = mapperWithDetails.toDto(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Result of single employee");
        //second method by using new ResponseEntity
        //return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employeeWithDetailsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeWithDetailsDTO> getEmployeeById(@PathVariable(required = true) Long id) {
        Employee employee = employeeManager.findEmployeeAndInfoById(id);
        EmployeeWithDetailsDTO employeeWithDetailsDTO = mapperWithDetails.toDto(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Result of single employee");
        //second method by using new ResponseEntity
        //return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employeeWithDetailsDTO);
    }

    @PostMapping
    //post adding elements
    //request body maps the HttpRequestBody to domain object, JSON -> Java type
    public ResponseEntity<Void> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        Employee employee = new Employee();
        Employee employee = mapper.toModel(employeeDTO);
        employeeManager.saveEmployee(employee);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Adding one employee");
        return ResponseEntity.status(HttpStatus.OK).headers(header).build();
        // or without header and body
        // return ResponseEntity.ok().build();
    }


    @PostMapping("/{id}/titles")
    public ResponseEntity<Void> addTitle(@RequestBody TitleDTO titleDTO, @PathVariable Long id) {
        Title title = titleMapper.toModel(titleDTO);
        employeeManager.addTitle(title, id);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Adding title to employee");
        return ResponseEntity.status(HttpStatus.OK).headers(header).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, Long id) {
        Employee employeeEdited = mapper.toModel(employeeDTO);
        employeeManager.editEmployee(employeeEdited, id);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Updating employee");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(employeeDTO);
    }


    @DeleteMapping("/{id}/titles")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long id, @RequestParam Long titleId) {
        employeeManager.deleteTitle(titleId);
        HttpHeaders header = new HttpHeaders();
        header.add("Description", "Delete title from employee");
        return ResponseEntity.status(HttpStatus.OK).headers(header).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeManager.deleteById(id);
        return ResponseEntity.ok().build();

    }

}
