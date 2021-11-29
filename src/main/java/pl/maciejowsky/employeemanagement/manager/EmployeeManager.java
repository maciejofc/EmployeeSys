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
import java.util.Optional;

@Service
public class EmployeeManager {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TitleRepository titleRepository;


    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

//    public List<Employee> getEmployeesWithTitles() {
//        List<Employee> allEmployees = (List<Employee>) employeeRepository.findAll();
//       List<Long> ids = allEmployees.stream()
//               .map(employee -> employee.getId())
//               .collect(Collectors.toList());
//
//        List<Title> titles = titleRepository.findAllByEmployeeIdIn(ids);
//        allEmployees.forEach(employee ->employee.setTitles(extractTitles(titles,employee.getId())));
//        return allEmployees;
//    }
//
//    private List<Title> extractTitles(List<Title> titles, Long id) {
//        return titles.stream()
//                .filter(title -> title.getEmployee().getId() == id)
//                .collect(Collectors.toList());
//    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        Employee employee1 = new Employee(new Date(1983, 10, 12), "Robert", "Nosalik", "nosek12@gmail.com", Gender.MALE, 4000);
        Employee employee2 = new Employee(new Date(1985, 11, 12), "Heniek", "Nosalik", "nosek16@gmail.com", Gender.FEMALE, 3000);


        save(employee1);

        Title title1= new Title("JUNIOR");
        Title title2 = new Title("MANAGER");
       Title title3 = new Title("CEO");
        employee1.getTitles().add(titleRepository.save(title1));employee1.getTitles().add(titleRepository.save(title2));
        employee1.getTitles().add(titleRepository.save(title3));
//
//        employee2.getTitles().add(titleRepository.save(title2));
//        employee2.getTitles().add(titleRepository.save(title3));
       save(employee1);
        //save(employee2);

    }
}
