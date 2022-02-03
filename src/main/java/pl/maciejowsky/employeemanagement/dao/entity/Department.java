package pl.maciejowsky.employeemanagement.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Department")
@Table(name = "departments")
public class Department {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "dept_no")
    private Long id;

    private String name;
    //

    @ManyToMany(mappedBy = "departments")
    private List<Employee> employees = new ArrayList<>();

    private String location;
    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Department(String name, String location) {
        this.name = name;
        this.location=location;
    }

}
