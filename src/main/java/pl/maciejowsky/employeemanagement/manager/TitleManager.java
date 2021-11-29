package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.TitleRepository;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import java.util.Optional;

@Service
public class TitleManager {

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;



    public Title addNewTitleToEmployee(Title title) {
        return titleRepository.save(title);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void addTitlesToEmployee() {
        Employee employee = employeeRepository.findByEmail("nosek@gmail.com");
        Employee employee1 = employeeRepository.findByEmail("nosek12@gmail.com");



    }
}
