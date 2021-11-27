package pl.maciejowsky.employeemanagement.dao.entity;

import javax.persistence.*;

@Entity(name = "Employee")
@Table(
        name = "employee"
//        uniqueConstraints = {
//                @UniqueConstraint(name = "student_email_unique", columnNames = "email")
//        }
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
            name = "email",
            nullable = false
    )
    private String email;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String name;
    @Column(
            name = "surname",
            nullable = false
    )
    private String surname;


    public Employee() {
    }

    public Employee(String email, String name, String surname) {
        this.email = email;
        this.name = name;
        this.surname = surname;

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

    public String getSurname() {

        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
