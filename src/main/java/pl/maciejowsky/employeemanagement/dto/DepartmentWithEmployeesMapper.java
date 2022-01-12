package pl.maciejowsky.employeemanagement.dto;

import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import pl.maciejowsky.employeemanagement.dao.entity.Department;

@Mapper(componentModel = "spring",
uses = {EmployeeMapper.class})
public interface DepartmentWithEmployeesMapper {
    @Mapping(source = "department.employees", target ="employeeDtoList")
    DepartmentWithEmployeesDTO toDto(Department department);
}
