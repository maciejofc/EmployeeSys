package pl.maciejowsky.employeemanagement.dto;

import pl.maciejowsky.employeemanagement.dao.Gender;
import pl.maciejowsky.employeemanagement.dao.entity.Department;

import java.util.List;
import java.util.Set;


public class EmployeeWithDetailsDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private int salary;
    private int age;
    private Set<TitleDTO> titlesDtoSet;
    private List<DepartmentDTO> departmentsDtoList;

    private EmployeeWithDetailsDTO(){

    }
    public static class Builder {
        private String firstName;
        private String lastName;
        private Gender gender;
        private int salary;
        private int age;
        private Set<TitleDTO> titlesDtoSet;
        private List<DepartmentDTO> departmentsDtoList;
        public Builder firstName(String firstName){
            this.firstName=firstName;
            return this;
        }
        public Builder lastName(String lastName){
            this.lastName=lastName;
            return this;
        }
        public Builder gender(Gender gender){
            this.gender=gender;
            return this;
        }
        public Builder salary(int salary){
            this.salary=salary;
            return this;
        }
        public Builder age(int age){
            this.age=age;
            return this;
        }
        public Builder titlesDtoSet(Set<TitleDTO> titlesDtoSet){
            this.titlesDtoSet=titlesDtoSet;
            return this;
        }
        public Builder departmentsDtoList(List<DepartmentDTO> departmentsDtoList){
            this.departmentsDtoList=departmentsDtoList;
            return this;
        }
        public EmployeeWithDetailsDTO build(){
            EmployeeWithDetailsDTO employeeWithDetailsDTO =  new EmployeeWithDetailsDTO();
            employeeWithDetailsDTO.firstName = this.firstName;
            employeeWithDetailsDTO.lastName=this.lastName;
            employeeWithDetailsDTO.age=this.age;
            employeeWithDetailsDTO.departmentsDtoList=this.departmentsDtoList;
            employeeWithDetailsDTO.titlesDtoSet=this.titlesDtoSet;
            employeeWithDetailsDTO.gender=this.gender;
            employeeWithDetailsDTO.salary=this.salary;
            return employeeWithDetailsDTO;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<TitleDTO> getTitlesDtoList() {
        return titlesDtoSet;
    }

    public void setTitlesDtoList(Set<TitleDTO> titlesDtoList) {
        this.titlesDtoSet = titlesDtoSet;
    }

    public List<DepartmentDTO> getDepartmentsDtoList() {
        return departmentsDtoList;
    }

    public void setDepartmentsDtoList(List<DepartmentDTO> departmentsDtoList) {
        this.departmentsDtoList = departmentsDtoList;
    }
    private EmployeeWithDetailsDTO(Builder b) {
        this.firstName = b.firstName;
        this.lastName=b.lastName;
        this.age=b.age;
        this.departmentsDtoList=b.departmentsDtoList;
        this.titlesDtoSet=b.titlesDtoSet;
        this.gender=b.gender;
        this.salary=b.salary;

    }
}
