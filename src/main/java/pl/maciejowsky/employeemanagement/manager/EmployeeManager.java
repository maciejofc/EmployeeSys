package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.TitleRepository;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;
import pl.maciejowsky.employeemanagement.dao.exception.ResourceAlreadyExistsException;
import pl.maciejowsky.employeemanagement.dao.exception.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeManager {

    private static final int PAGE_SIZE = 3;

    @PersistenceContext
    private EntityManager entityManager;

    private TitleRepository titleRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository,
                           TitleRepository titleRepository) {
        this.employeeRepository = employeeRepository;
        this.titleRepository = titleRepository;
    }


    public List<Employee> findAllEmployeesWithInfo(int page, Sort sort) {


        List<Employee> allEmployees = employeeRepository.findAllEmployeesWithInfo(
                PageRequest.of(page, PAGE_SIZE,sort));

        List<Long> ids = allEmployees.stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        List<Title> titles = titleRepository.findAllByEmployeeIdIn(ids);
        Set<Title> setTitles = new HashSet<>(titles);
        allEmployees.forEach(employee -> employee.setTitles(extractTitles(setTitles,employee.getId())));
        return allEmployees;
    }

    private Set<Title> extractTitles(Set<Title> titles, Long id) {
        return titles.stream()
                .filter(title -> title.getEmployeeId().equals(id))
                .collect(Collectors.toSet());
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
        entityManager.persist(employee1);
        Employee employee2 = new Employee(new Date(1985, 11, 12), "Robert", "Nosalik", "nosek9@gmail.com", Gender.FEMALE, 30030);
        Employee employee3 = new Employee(new Date(1925, 11, 12), "Maciek", "Nosalik", "nosek8@gmail.com", Gender.FEMALE, 31000);
        Employee employee4 = new Employee(new Date(1924, 11, 12), "Tosia", "Nosalik", "nosek6@gmail.com", Gender.FEMALE, 30040);

        employee1.getTitles().add(new Title("JUNIOR"));
        employee1.getTitles().add(new Title("MANAGER"));
        employee1.getTitles().add(new Title("CEO"));


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
