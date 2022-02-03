package pl.maciejowsky.employeemanagement.dto;

import org.mapstruct.Mapper;
import pl.maciejowsky.employeemanagement.dao.entity.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

        EmployeeDTO toDto(Employee employee);
        Employee toModel(EmployeeDTO employeeDTO);
}
