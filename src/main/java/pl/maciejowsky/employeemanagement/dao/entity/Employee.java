package pl.maciejowsky.employeemanagement.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pl.maciejowsky.employeemanagement.dao.Gender;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name = "Employee")
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(name = "employee_email_unique", columnNames = "email")
        }
)
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(
            name = "emp_no",
            updatable = false
    )
    private Long id;


    @Column(
            name = "birth_date",
            nullable = false
    )
    private Date birthDate;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;
    @Column(
            name = "gender",
            nullable = false
    )
    @Enumerated(EnumType.STRING)

    private Gender gender;
    @Column(
            name = "hire_date",
            nullable = false
    )
    private Date hireDate;
    @Column(
            name = "salary",
            nullable = false
    )
    private int salary;
    //mapped by is value that we reference in title
    // this is Employee  --->>>employee<<<----
    //if we do not specify mappedBy we will have
    // separate table employee_title
    // -------DEFAULTS-------
    //    OneToMany: LAZY
//    ManyToOne: EAGER
//    ManyToMany: LAZY
//    OneToOne: EAGER
    //orphan removal(ORM concept) make a DELETE statement to child when
    //there is no longer association

    //CascadeType.Remove(db concept) if parent is removed all related records in child
    //table should be removed
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    //updatable true and insertable
    // by default makes we can pass titles into request body
    //when we insert or update
    @JoinColumn(name = "emp_no_foreign_key", referencedColumnName = "emp_no", insertable = true, updatable = true)
    private Set<Title> titles = new HashSet<>();
    //

    @ManyToMany()
    @JoinTable(
            joinColumns = {@JoinColumn(name = "emp_no")},
            inverseJoinColumns = {@JoinColumn(name = "dept_no")}
    )

    List<Department> departments = new ArrayList<>();

    public void addDepartment(Department department) {
        departments.add(department);
        department.getEmployees().add(this);
    }

    public Date getBirthDate() {

        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {

        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {

        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Title> getTitles() {

        return titles;
    }


    public void setTitles(Set<Title> titles) {
        this.titles = titles;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public Employee() {
        this.hireDate = Date.valueOf(LocalDate.now());
    }

    public Employee(Date birthDate, String firstName, String lastName, String email, Gender gender, int salary) {
        int year = birthDate.getYear() - 1900;
        int month = birthDate.getMonth();
        int second = birthDate.getDay();
        this.birthDate = new Date(year, month, second);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.salary = salary;
        this.hireDate = Date.valueOf(LocalDate.now());
    }

}
