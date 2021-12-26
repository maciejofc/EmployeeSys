package pl.maciejowsky.employeemanagement.employee;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//Autowiring employee rep
        //Spinning the db
class EmployeeRepositoryTest {

    //Tests for methods that  we have implemented in repository
    @Autowired
    private EmployeeRepository underTest;

    @BeforeEach
    void doBeforeTest(){
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfEmployeeExistsByEmail() {

        //given
        String exemplaryEmail = "franczesko997@gmail.com";
        Employee exemplaryEmployee = new Employee(new Date(1900, 12, 11), "Antek", "Nosalik", exemplaryEmail, Gender.MALE, 3000);
        underTest.save(exemplaryEmployee);
        //when
        Employee result = underTest.findEmployeeAndInfoByEmail(exemplaryEmail);

        //then
        assertThat(result.getEmail()).isEqualTo("franczesko997@gmail.com");
    }
    @Test
    void itShouldCheckIfEmployeeDoesNotExistsByEmail(){
        //given
        String exemplaryEmail = "franczesko997@gmail.com";

        //when
        Employee result =underTest.findEmployeeAndInfoByEmail(exemplaryEmail);
        //then
        assertThat(result).isNull();
    }

    @Test
    void itShouldCheckIfEmployeeExistsById() {
        //given
        Employee exemplaryEmployee = new Employee(new Date(1500, 12, 11), "Antek", "Nosalik", "frko997222@gmail.com", Gender.MALE, 34000);
        underTest.save(exemplaryEmployee);
        //when
        //id 2 not 1 because identity strategy makes that
        // wheter we delete records strategy look at last added Id and return
        //new id = previous id +1
        Employee result = underTest.findEmployeeAndInfoById(2L);

        //then

        assertThat(result.getEmail()).isEqualTo("frko997222@gmail.com");
    }
    @Test
    void itShouldCheckIfEmployeeDoesNotExistsById() {
        //given
        long id =1;

        //when
        Employee result = underTest.findEmployeeAndInfoById(1L);

        //then

        assertThat(result).isNull();
    }

    @Test
    void itShouldCheckIfFindAllEmployesWithInfo(){
        //given
        Title title1=new Title("JUNIOR");
        Title title2 =new Title("NOONE");

        Employee exemplaryEmployee1 = new Employee(new Date(1500, 12, 11), "Antek", "Nosalik", "frko997222@gmail.com", Gender.MALE, 34000);
        Employee exemplaryEmployee2 = new Employee(new Date(1700, 11, 11), "Antek", "Nosalik", "fr22@gmail.com", Gender.MALE, 300);

        exemplaryEmployee1.getTitles().add(title1);
        exemplaryEmployee2.getTitles().add(title2);
        underTest.save(exemplaryEmployee1);
        underTest.save(exemplaryEmployee2);
        //when
        List<Employee> resultList = underTest.findAllEmployeesWithInfo();

        //then
        assertThat(resultList).containsExactly(exemplaryEmployee1,exemplaryEmployee2);
    }

    @Test
    void itShouldCheckIfNotFindAllEmployesWithInfo(){
        //given nothing


        //when
        List<Employee> resultList = underTest.findAllEmployeesWithInfo();

        //then
        assertThat(resultList.isEmpty()).isTrue();

    }



}