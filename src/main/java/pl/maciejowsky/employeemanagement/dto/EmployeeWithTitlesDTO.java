package pl.maciejowsky.employeemanagement.dto;

import pl.maciejowsky.employeemanagement.dao.Gender;

import java.util.Set;

public class EmployeeWithTitlesDTO {
    private String firstName;
    private String lastName;
    private Gender gender;
    private int salary;
    private int age;
    private String email;
    private Set<TitleDTO> titlesDtoSet;

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

    private EmployeeWithTitlesDTO() {

    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private Gender gender;
        private int salary;
        private int age;
        private String email;
        private Set<TitleDTO> titlesDtoSet;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder salary(int salary) {
            this.salary = salary;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder titlesDtoSet(Set<TitleDTO> titlesDtoSet) {
            this.titlesDtoSet = titlesDtoSet;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeeWithTitlesDTO build() {
            EmployeeWithTitlesDTO employeeWithTitlesDTO = new EmployeeWithTitlesDTO();
            employeeWithTitlesDTO.firstName = this.firstName;
            employeeWithTitlesDTO.lastName = this.lastName;
            employeeWithTitlesDTO.age = this.age;
            employeeWithTitlesDTO.titlesDtoSet = this.titlesDtoSet;
            employeeWithTitlesDTO.gender = this.gender;
            employeeWithTitlesDTO.salary = this.salary;
            employeeWithTitlesDTO.email = this.email;
            return employeeWithTitlesDTO;
        }
    }

    private EmployeeWithTitlesDTO(Builder b) {
        this.firstName = b.firstName;
        this.lastName = b.lastName;
        this.age = b.age;
        this.titlesDtoSet = b.titlesDtoSet;
        this.gender = b.gender;
        this.salary = b.salary;
        this.email = b.email;
    }
}
