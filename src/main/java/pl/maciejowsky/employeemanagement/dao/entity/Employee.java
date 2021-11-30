package pl.maciejowsky.employeemanagement.dao.entity;

import org.hibernate.annotations.BatchSize;
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
    private long id;


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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_no_foreign_key", referencedColumnName = "emp_no")
    private Set<Title> titles= new HashSet<>();
    public Set<Title> getTitles() {

        return titles;
    }

    public void setTitles(Set<Title> titles) {
        this.titles = titles;
    }

    public Employee() {
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

    public Employee(Date birthDate, String firstName, String lastName, String email, Gender gender, int salary) {
        int year = birthDate.getYear()-1900;
        int month = birthDate.getMonth();
        int second = birthDate.getDay();
        this.birthDate =new Date(year,month,second);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.salary = salary;
        this.hireDate = Date.valueOf(LocalDate.now());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
