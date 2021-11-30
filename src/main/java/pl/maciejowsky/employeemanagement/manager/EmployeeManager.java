package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.TitleRepository;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import java.sql.Date;
import java.util.List;

@Service
public class EmployeeManager {
    @Autowired
    private EmployeeRepository employeeRepository;




    public Employee findByEmail(String email) {
        return employeeRepository.findEmployeeAndInfoByEmail(email);
    }
    public Employee findById(long id) {
        return employeeRepository.findEmployeeAndInfoById(id);
    }



    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    public List<Employee> findAll() {
        return employeeRepository.findAllEmployeesWithInfo();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        Employee employee1 = new Employee(new Date(1983, 10, 12), "Robert", "Nosalik", "nosek10@gmail.com", Gender.MALE, 4000);
        Employee employee2 = new Employee(new Date(1985, 11, 12), "Robert", "Nosalik", "nosek9@gmail.com", Gender.FEMALE, 30030);
        Employee employee3 = new Employee(new Date(1925, 11, 12), "Maciek", "Nosalik", "nosek8@gmail.com", Gender.FEMALE, 31000);
        Employee employee4 = new Employee(new Date(1924, 11, 12), "Tosia", "Nosalik", "nosek6@gmail.com", Gender.FEMALE, 30040);
        Employee employee5 = new Employee(new Date(1926, 11, 12), "Agnieszka", "Nosalik", "nosek5@gmail.com", Gender.FEMALE, 300510);
        Employee employee6 = new Employee(new Date(1921, 11, 12), "Milosz", "Nosalik", "nosek4@gmail.com", Gender.FEMALE, 303400);
        Employee employee7 = new Employee(new Date(1922, 11, 12), "Antek", "Nosalik", "nosek3@gmail.com", Gender.FEMALE, 300430);
        Employee employee8 = new Employee(new Date(1923, 11, 12), "Franek", "Nosalik", "nosek2@gmail.com", Gender.FEMALE, 3000);
        Employee employee9 = new Employee(new Date(1924, 11, 12), "Basia", "Nosalik", "nosek1@gmail.com", Gender.FEMALE, 300430);
        save(employee1);
        save(employee2);
        save(employee3);
        save(employee4);
        save(employee5);
        save(employee6);
        save(employee7);
        save(employee8);
        save(employee9);

        Title title1 = new Title("JUNIOR");
        Title title2 = new Title("MANAGER");
        Title title3 = new Title("CEO");

        employee1.getTitles().add(title1);
        employee1.getTitles().add(title2);
        employee1.getTitles().add(title3);

        employee2.getTitles().add(title2);
        employee2.getTitles().add(title3);
        employee3.getTitles().add(title3);
        employee4.getTitles().add(title3);
        employee4.getTitles().add(title2);
        employee4.getTitles().add(title1);
        employee5.getTitles().add(title3);
        employee6.getTitles().add(title3);
        employee6.getTitles().add(title2);
        employee6.getTitles().add(title1);
        employee7.getTitles().add(title3);
        employee8.getTitles().add(title3);
        employee9.getTitles().add(title3);
        employee9.getTitles().add(title2);


        save(employee1);
        save(employee2);
        save(employee3);
        save(employee4);
        save(employee5);
        save(employee6);
        save(employee7);
        save(employee8);
        save(employee9);
    }
}
