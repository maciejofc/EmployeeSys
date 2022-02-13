package pl.maciejowsky.employeemanagement.employee;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//Autowiring employee rep
        //Spinning the db
class EmployeeRepositoryTest {

    //Tests for methods that  we have implemented in repository
    @Autowired
    private EmployeeRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();

    }

//    @Test
//    void itShouldCheckIfEmployeeExistsById() {
//        //given
//        Long exemparyId = 2L;
//
//        Employee exemplaryEmployee = new Employee(new Date(1900, 12, 11), "Antek", "Nosalik", "franczesko997@gmail.com", Gender.MALE, 3000);
//        Employee exemplaryEmployee2 = new Employee(new Date(1900, 12, 11), "Antek", "Nosalik", "wwwwwwwwww@gmail.com", Gender.MALE, 3000);
//        List<Employee> exemplaryEmployees = new ArrayList<>();
//        exemplaryEmployees.add(exemplaryEmployee);
//        exemplaryEmployees.add(exemplaryEmployee2);
//        underTest.saveAll(exemplaryEmployees);
//        //when
//        Long expected = underTest.findEmployeeAndInfoById(exemparyId).get().getId();
//        //then
//
//        assertThat(expected).isEqualTo(exemparyId);
//    }

    @Test
    void itShouldCheckIfEmployeeDoesNotExistsById() {
        //given
        Long exemparyId = 1L;

        //when
        Optional<Employee> expectedEmployee = underTest.findEmployeeAndInfoById(exemparyId);
        boolean expected = expectedEmployee.isPresent();
        //then

        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckIfEmployeeExistsByEmail() {

        //given
        String exemplaryEmail = "franczesko997@gmail.com";
        Employee exemplaryEmployee = new Employee(new Date(1900, 12, 11), "Antek", "Nosalik", exemplaryEmail, Gender.MALE, 3000);
        underTest.save(exemplaryEmployee);
        //when
        String expectedEmail = underTest.findEmployeeAndInfoByEmail(exemplaryEmail).get().getEmail();
        boolean expected = expectedEmail.equals(exemplaryEmail);

        //then

        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfEmployeeDoesNotExistsByEmail() {
        //given
        String exemplaryEmail = "franczesko997@gmail.com";

        //when

        Optional<Employee> expectedEmployee = underTest.findEmployeeAndInfoByEmail(exemplaryEmail);
        boolean expected = expectedEmployee.isPresent();

        //then
        assertThat(expected).isFalse();
    }


    @Test
    void itShouldCheckIfFindsAllEmployesWithInfo() {
        //given

        Employee exemplaryEmployee1 = new Employee(new Date(1500, 12, 11), "Antek", "Nosalik", "frko997222@gmail.com", Gender.MALE, 34000);
        Employee exemplaryEmployee2 = new Employee(new Date(1700, 11, 11), "Antek", "Nosalik", "fr22@gmail.com", Gender.MALE, 300);
        underTest.save(exemplaryEmployee1);
        underTest.save(exemplaryEmployee2);
        Title exemplaryTitle1 = new Title("JUNIOR");
        Title exemplaryTitle2 = new Title("NOONE");


        exemplaryEmployee1.addTitle(exemplaryTitle1);
        exemplaryEmployee2.addTitle(exemplaryTitle2);
        exemplaryEmployee1.addDepartment(new Department("HHEHE", "Gdynia"));

        //when
        List<Employee> resultList = underTest.findAllEmployeesWithInfo(Pageable.ofSize(2));

        //then
        assertThat(resultList).containsExactly(exemplaryEmployee1, exemplaryEmployee2);

    }

    @Test
    void itShouldCheckIfNotFindAllEmployesWithInfo() {
        //given nothing


        //when
        List<Employee> resultList = underTest.findAllEmployeesWithInfo(Pageable.ofSize(2));

        //then
        assertThat(resultList.isEmpty()).isTrue();

    }

//
//    @Test
//    void itShouldCheckIfAddsTitle() {
//        //given
//        Employee exemplaryEmployee = new Employee(new Date(1900, 12, 11), "Antek", "Nosalik", "franczesko997@gmail.com", Gender.MALE, 3000);
//        underTest.save(exemplaryEmployee);
//        Long id = exemplaryEmployee.getId();
//        //when
//        underTest.addTitle("title",id);
//        String expected =exemplaryEmployee.getTitles().iterator().next().getTitle();
//        //then
//        assertThat(expected).isEqualTo("title");
//    }
//    @Test
//    void itShouldCheckIfDoesNotAddTitle() {
////        //given
////        String title="title";
////
////        //when
////        underTest.addTitle(title,1L);
////
////        //then
//
//    }

//    @Test
//    void ItShouldCheckIfDeletesTitle() {
//        //given
//        Employee exemplaryEmployee = new Employee(new Date(1900, 12, 11), "Antek", "Nosalik", "franczesko997@gmail.com", Gender.MALE, 3000);
//        underTest.save(exemplaryEmployee);
//        Title title = new Title("title");
//        exemplaryEmployee.addTitle(title);
//        Long titleId = underTest.findEmployeeAndInfoByEmail("franczesko997@gmail.com").get().getTitles().iterator().next().getId();
//        //when
//        underTest.deleteTitle(titleId);
//        String expected =exemplaryEmployee.getTitles().iterator().next().getTitle();
//        //then
//        assertThat(expected).isNullOrEmpty();
//    }
}
