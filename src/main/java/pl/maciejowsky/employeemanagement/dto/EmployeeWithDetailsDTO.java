package pl.maciejowsky.employeemanagement.dto;

import pl.maciejowsky.employeemanagement.dao.Gender;

import java.util.List;
import java.util.Set;


public class EmployeeWithDetailsDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private int salary;
    private int age;
    private String email;
    private Set<TitleDTO> titlesDtoSet;
    private List<DepartmentDTO> departmentsDtoList;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public Set<TitleDTO> getTitlesDtoSet() {
        return titlesDtoSet;
    }

    public List<DepartmentDTO> getDepartmentsDtoList() {
        return departmentsDtoList;
    }

    private EmployeeWithDetailsDTO(){

    }
    public static class Builder {
        private String firstName;
        private String lastName;
        private Gender gender;
        private int salary;
        private int age;
        private String email;
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
        public Builder email(String email){
            this.email=email;
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
            employeeWithDetailsDTO.email=this.email;
            return employeeWithDetailsDTO;
        }
    }

    private EmployeeWithDetailsDTO(Builder b) {
        this.firstName = b.firstName;
        this.lastName=b.lastName;
        this.age=b.age;
        this.departmentsDtoList=b.departmentsDtoList;
        this.titlesDtoSet=b.titlesDtoSet;
        this.gender=b.gender;
        this.salary=b.salary;
        this.email=b.email;

    }
}
