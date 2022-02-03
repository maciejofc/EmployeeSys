package pl.maciejowsky.employeemanagement.dto;

import org.mapstruct.Mapper;
import pl.maciejowsky.employeemanagement.dao.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentDTO toDto(Department department);
    Department toModel(DepartmentDTO departmentDTO);
}
