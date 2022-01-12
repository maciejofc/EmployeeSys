package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@Service
public class EmployeeManager {


    @PersistenceContext
    private EntityManager entityManager;

    //    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee findByEmail(String email) {
        return employeeRepository.findEmployeeAndInfoByEmail(email);
    }

    public Employee findById(Long id) {
        return employeeRepository.findEmployeeAndInfoById(id);
    }

    //we make 2 transaction em
    @Transactional
    //dirty checking - hibernate checks if the entity is updated
    //and auto save to db
    public Employee editEmployee(Employee employee) {
        Employee employeeEdited = employeeRepository.findEmployeeAndInfoById(employee.getId());
        employeeEdited.setFirstName(employee.getFirstName());
        employeeEdited.setLastName(employee.getLastName());
        employeeEdited.setTitles(employee.getTitles());
        employeeEdited.setEmail(employee.getEmail());
        employeeEdited.setSalary(employee.getSalary());
        //dirty checking - we can just do - return employeeEdited
        return employeeRepository.save(employeeEdited);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAllEmployeesWithInfo();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }






    public List<Employee> getAllEmployeesWithInfo(){
    return employeeRepository.findAllEmployeesWithInfo();
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
        Department department =new Department("Department 1","Gdynia");
        Department department2 =new Department("Department 2","Warszawa");
        Department department3 =new Department("Department 3","Sopot");


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
