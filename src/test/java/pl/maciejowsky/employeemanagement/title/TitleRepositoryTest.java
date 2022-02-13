package pl.maciejowsky.employeemanagement.title;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.TitleRepository;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class TitleRepositoryTest {
@Autowired
private TitleRepository underTest;


    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }



//    @Test
//    void itShouldCheckIfAddsTitle() {
//        //given
//        Employee exemplaryEmployee = new Employee(new Date(1900, 12, 11), "Antek", "Nosalik", "franczesko997@gmail.com", Gender.MALE, 3000);
//        underTest.save(exemplaryEmployee);
//        String nameOfExemplaryTitle = "title";
//        //when
//        underTest.addTitle(nameOfExemplaryTitle,exemplaryEmployee.getId());
//        String expected =exemplaryEmployee.getTitles().iterator().next().getTitle();
//        //then
//        assertThat(expected).isEqualTo(nameOfExemplaryTitle);
//    }
//    @Test
//    void itShouldCheckIfDoesNotAddTitle() {
//        //given
//        String title="title";
//
//        //when
//        underTest.addTitle(title,1L);
//
//        //then
//        assertThat()
//    }
//
//    @Test
//    void deleteTitle() {
//    }
}