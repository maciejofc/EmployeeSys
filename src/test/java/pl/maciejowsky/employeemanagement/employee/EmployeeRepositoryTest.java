package pl.maciejowsky.employeemanagement.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

import java.sql.Date;

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
        String exemplaryEmail = "franczesko997@gmail.com";
        Employee exemplaryEmployee = new Employee(new Date(1900,12,11), "Antek", "Nosalik",exemplaryEmail, Gender.MALE,3000);
        underTest.save(exemplaryEmployee);
        //when
        Employee result = underTest.findEmployeeAndInfoByEmail(exemplaryEmail);

        //then
        assertThat(result.getEmail()).isEqualTo("franczesko997@gmail.com");
    }
}