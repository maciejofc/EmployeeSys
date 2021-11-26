package pl.maciejowsky.employeemanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EmployeemanagementApplicationTests {
	Calculator calculator = new Calculator();
	@Test
	void itShouldAddNumbers() {
		//given
		int numberOne = 20;
		int numberTwo = 30;

		//when
		int result = calculator.add(numberOne,numberTwo);

		//then
		int expected =50;
		assertThat(result).isEqualTo(expected);
	}
	class Calculator {
		private int a;
		private int b;


		int add (int a,int b){
			return a+b;
		}
	}

}
