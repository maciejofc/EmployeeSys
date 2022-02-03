package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;
import pl.maciejowsky.employeemanagement.dao.exception.ResourceAlreadyExistsException;
import pl.maciejowsky.employeemanagement.dao.exception.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@Service
public class EmployeeManager {



    @PersistenceContext
    private EntityManager entityManager;


    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> findAllEmployeesWithInfo() {
        return employeeRepository.findAllEmployeesWithInfo();
    }

    public Employee findEmployeeAndInfoByEmail(String email) {
        return employeeRepository.findEmployeeAndInfoByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("There is no member associated with this email:" + email));
    }

    public Employee findEmployeeAndInfoById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("There is no member associated with this id: " + id));
    }

    public Employee saveEmployee(Employee employee) {
        String email = employee.getEmail();
        boolean ifEmailExists = employeeRepository.findEmployeeAndInfoByEmail(email).isPresent();
        if (!ifEmailExists) {
            return employeeRepository.save(employee);
        } else
            throw new ResourceAlreadyExistsException("This email:" + email + " is already associated with some member, please try another email");

    }

    @Transactional
    public Employee editEmployee(Employee employee, Long id) {
        String newEmail = employee.getEmail();
        boolean isInDBAlreadyExactEmail = employeeRepository.findEmployeeAndInfoByEmail(newEmail).isPresent();
        if (isInDBAlreadyExactEmail)
            throw new ResourceAlreadyExistsException("You can not change your e-mail to this: " + newEmail + " because it is occupied");
        Employee employeeFromDB = findEmployeeAndInfoById(id);
        if (employee.getFirstName() != null) {
            employeeFromDB.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            employeeFromDB.setLastName(employee.getLastName());
        }
        if (employee.getEmail() != null) {

            employeeFromDB.setEmail(employee.getEmail());

        }

        if (Integer.valueOf(employee.getSalary()) != null) {
            employeeFromDB.setSalary(employee.getSalary());
        }

        if (employee.getGender() != null) {
            employeeFromDB.setGender(employee.getGender());
        }

        return employeeFromDB;
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }


    @Transactional
    public void addTitle(Title title, Long empNo) {
        Employee employee = findEmployeeAndInfoById(empNo);
        for (Title iteratingTitle : employee.getTitles()) {
            String nameOfAlreadyExistingTitle = iteratingTitle.getTitle();
            if (nameOfAlreadyExistingTitle.equals(title.getTitle())) {
                throw new ResourceAlreadyExistsException("This title: " + nameOfAlreadyExistingTitle + " is already assigned to member");
            }
        }

        employee.getTitles().add(title);
    }


    @Transactional
    public void deleteTitle(Long id) {
        employeeRepository.deleteTitle(id);
    }


    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        Employee employee1 = new Employee(new Date(1983, 10, 12), "Robert", "Nosalik", "nosek10@gmail.com", Gender.MALE, 4000);
        Employee employee2 = new Employee(new Date(1985, 11, 12), "Robert", "Nosalik", "nosek9@gmail.com", Gender.FEMALE, 30030);
        Employee employee3 = new Employee(new Date(1925, 11, 12), "Maciek", "Nosalik", "nosek8@gmail.com", Gender.FEMALE, 31000);
        Employee employee4 = new Employee(new Date(1924, 11, 12), "Tosia", "Nosalik", "nosek6@gmail.com", Gender.FEMALE, 30040);


        employee1.getTitles().add(new Title("JUNIOR"));
        employee1.getTitles().add(new Title("MANAGER"));
        employee1.getTitles().add(new Title("CEO"));
        entityManager.persist(employee1);

        employee2.getTitles().add(new Title("JUNIOR"));
        employee2.getTitles().add(new Title("NOONE"));
        entityManager.persist(employee2);
        employee3.getTitles().add(new Title("A ONE"));
        entityManager.persist(employee3);
        entityManager.persist(employee4);
        Department department = new Department("Department 1", "Gdynia");
        Department department2 = new Department("Department 2", "Warszawa");
        Department department3 = new Department("Department 3", "Sopot");


        employee2.addDepartment(department2);
        employee2.addDepartment(department3);
        employee1.addDepartment(department);
        employee4.addDepartment(department);
        employee3.addDepartment(department2);
        entityManager.persist(department);
        entityManager.persist(department2);
        entityManager.persist(department3);
    }
}
