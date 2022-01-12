package pl.maciejowsky.employeemanagement.dto;

import org.mapstruct.Mapper;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;
import pl.maciejowsky.employeemanagement.dao.entity.Title;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(
        componentModel = "spring"
)
public interface EmployeeWithDetailsMapper {

    int ACTUAL_YEAR = Year.now().getValue();

    //    @Mapping(source = "employee.titles", target = "titlesDtoList")
//    @Mapping(source = "employee.departments", target = "departmentsDtoList")
    static int calculateAge(int yearOfBirth) {
        return ACTUAL_YEAR - yearOfBirth;
    }

    static List<DepartmentDTO> departmentToDto(List<Department> departments) {
        List<DepartmentDTO> result = new ArrayList<>();
        for (Department d : departments) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setName(d.getName());
            departmentDTO.setLocation(d.getLocation());
            result.add(departmentDTO);
        }
        return result;
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

    default EmployeeWithDetailsDTO toDto(Employee employee) {


        EmployeeWithDetailsDTO employeeWithDetailsDTO = new EmployeeWithDetailsDTO.Builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .age(calculateAge(employee.getBirthDate().getYear() + 1900))
                .gender(employee.getGender())
                .salary(employee.getSalary())
                .departmentsDtoList(departmentToDto(employee.getDepartments()))
                .titlesDtoSet(titleToDto(employee.getTitles()))
                .build();

        return employeeWithDetailsDTO;
    }
}
