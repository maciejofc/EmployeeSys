package pl.maciejowsky.employeemanagement.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//Autowiring employee rep
    //Spinning the db
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository underTest;

    @Test
    void itShouldCheckIfEmployeeExistsByEmail() {

        //given
        String exemplaryEmail = "nosek@gmail.com";
        Employee exemplaryEmployee = new Employee(exemplaryEmail, "Antek", "Nosalik");
        underTest.save(exemplaryEmployee);
        //when
        Employee result = underTest.findByEmail(exemplaryEmail);

        //then
        assertThat(result.getEmail()).isEqualTo("nosek@gmail.com");
    }
}