//package pl.maciejowsky.employeemanagement.manager;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import pl.maciejowsky.employeemanagement.dao.EmployeeRepository;
//
//import static org.mockito.Mockito.verify;
///// replaced***
//@ExtendWith(MockitoExtension.class)
//class EmployeeManagerTest {
//    @Mock private EmployeeRepository employeeRepository;
//    private  AutoCloseable autoCloseable;
//    private EmployeeManager underTest;
//    @BeforeEach
//    void setUp() {
//        //initializing employeeREpository
//  //***      autoCloseable = MockitoAnnotations.openMocks(this);
//        underTest = new EmployeeManager(employeeRepository);
//    }
//
////***    @AfterEach
////***    void tearDown() throws Exception {
////***       autoCloseable.close();
////***    }
//
//
//    @Test
//    void canFindAllEmployees(){
//        //when
//       underTest.findAllEmployeesWithInfo();
//        //then
//        //we check if this repository was invoked using the method
//        verify(employeeRepository).findAllEmployeesWithInfo();
//    }
//
//
//    @Test
//
//    void findByEmail() {
//        //when
//        underTest.findEmployeeAndInfoByEmail("something@gmail.com");
//
//        //then
//        verify(employeeRepository).findEmployeeAndInfoByEmail("something@gmail.com");
//    }
//
//
//
//    @Test
//
//    void findById() {
//        //when
//        underTest.findEmployeeAndInfoById(1L);
//
//        //then
//        verify(employeeRepository).findEmployeeAndInfoById(1L);
//    }
//}