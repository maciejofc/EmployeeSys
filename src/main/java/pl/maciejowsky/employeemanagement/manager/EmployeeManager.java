package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

import java.sql.Date;
import java.util.Optional;

@Service
public class EmployeeManager {
    @Autowired
    private EmployeeRepository employeeRepository;



    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new Employee(new Date(1985,11,12),"Antek", "Nosalik","nosek@gmail.com", Gender.MALE,2000));
        save(new Employee(new Date(1983,10,12),"Robert", "Nosalik","nosek12@gmail.com", Gender.MALE,4000));
        save(new Employee(new Date(1985,11,12),"Heniek", "Nosalik","nosek16@gmail.com", Gender.FEMALE,3000));
    }
}
