package pl.maciejowsky.employeemanagement.dto;

import java.util.List;

public class DepartmentWithEmployeesDTO {
private String name;
private String location;
private List<EmployeeDTO> employeeDtoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<EmployeeDTO> getEmployeeDtoList() {
        return employeeDtoList;
    }

    public void setEmployeeDtoList(List<EmployeeDTO> employeeDtoList) {
        this.employeeDtoList = employeeDtoList;
    }

}
