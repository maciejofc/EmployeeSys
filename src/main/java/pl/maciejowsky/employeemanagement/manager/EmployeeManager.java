package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

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
        save(new Employee("nosek@gmail.com","Antek", "Nosalik" ));
        save(new Employee( "franek@gmail.com","Franek", "Antkowiak" ));
        save(new Employee( "franedsadsak@gmail.com","Franekdsad", "Antkowiadsadsak" ));
    }
}
