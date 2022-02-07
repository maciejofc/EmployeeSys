package pl.maciejowsky.employeemanagement.dto;

import org.mapstruct.Mapper;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeWithTitlesMapper {
    int ACTUAL_YEAR = Year.now().getValue();
    static int calculateAge(int yearOfBirth) {
        return ACTUAL_YEAR - yearOfBirth;
    }
    static Set<TitleDTO> titleToDto(Set<Title> titles) {
        Set<TitleDTO> result = new HashSet<>();
        for (Title t : titles) {
            TitleDTO titleDTO = new TitleDTO();
            titleDTO.setTitle(t.getTitle());
            result.add(titleDTO);
        }
        return result;
    }
    default EmployeeWithTitlesDTO toDto(Employee employee) {


        EmployeeWithTitlesDTO employeeWithTitlesDTO = new EmployeeWithTitlesDTO.Builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .age(calculateAge(employee.getBirthDate().getYear() + 1900))
                .gender(employee.getGender())
                .salary(employee.getSalary())
                .email(employee.getEmail())
                .titlesDtoSet(titleToDto(employee.getTitles()))
                .build();

        return employeeWithTitlesDTO;
    }
}
