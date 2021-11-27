package pl.maciejowsky.employeemanagement.dao.entity;

import pl.maciejowsky.employeemanagement.dao.Gender;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


@Entity(name = "Employee")
@Table(
        name = "employee",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(
            name = "id",
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
    private Integer salary;


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

    public Employee(Date birthDate, String firstName, String lastName, String email, Gender gender, Integer salary) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.salary = salary;
        this.hireDate = Date.valueOf(LocalDate.now());
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


    public Integer getSalary() {

        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
