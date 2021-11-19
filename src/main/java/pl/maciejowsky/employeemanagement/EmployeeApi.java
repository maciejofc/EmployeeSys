package pl.maciejowsky.employeemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
//Every method returns a domain object instead of a view
//ResponseBody is not required
//Every request handling method of the controller class automatically serializes return objects into HttpResponse.


@RequestMapping("/api/employees")
//Request mapping tells that the resources are available at ceratain endpoint
public class EmployeeApi {
    private List<Employee> employeeList;

    public EmployeeApi() {
        employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L,"Antek","Nosalik","nosek@gmail.com"));
        employeeList.add(new Employee(2L,"Franek","Antkowiak","franek@gmail.com"));
    }
    @GetMapping("/all")
    public List<Employee> getAll(){
        return employeeList;
    }

    @GetMapping
    public Employee getById(@RequestParam int index){
        Optional<Employee> first =employeeList.stream().filter(element -> element.getId() == index).findFirst();
        return first.get();

    }
    @PostMapping
    //post adding elements
    public boolean addVideo(@RequestBody Employee employee){
        return employeeList.add(employee);

    }
    @PutMapping
    //PatchMapping only updating one field
    //overriding elements - updating
    public boolean updateVideo(@RequestBody Employee employee){
        return employeeList.add(employee);


    }
    @DeleteMapping
    public boolean deleteVideo(@RequestParam int index){
        return employeeList.removeIf(employee -> employee.getId()== index);

    }

}
