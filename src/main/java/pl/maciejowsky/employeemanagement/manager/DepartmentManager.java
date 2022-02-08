package pl.maciejowsky.employeemanagement.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.maciejowsky.employeemanagement.dao.DepartmentRepository;
import pl.maciejowsky.employeemanagement.dao.entity.Department;
import pl.maciejowsky.employeemanagement.dao.exception.ResourceAlreadyExistsException;
import pl.maciejowsky.employeemanagement.dao.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class DepartmentManager {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> findAllDepartmentsWithEmployee() {
        return departmentRepository.findAllDepartmentsWithEmployee();
    }

    public Department findDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No department with id: " + id));
    }

    public Department findDepartmentByName(String name) {
        return departmentRepository.findDepartmentByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("There is no department with this name"));
    }

    @Transactional
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Transactional
    public void saveEmployeeToDepartment(Long employeeId, Long departmentId) {
        Long result = departmentRepository.checkIfThereIsAlreadyEmployeeInDepartment(employeeId, departmentId);
        if (result != null) {
            throw new ResourceAlreadyExistsException("There is already employee in department with id: " + departmentId);
        } else {
            departmentRepository.saveEmployeeToDepartment(employeeId, departmentId);
        }
    }

    @Transactional
    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Transactional
    public void deleteEmployeeFromDepartment(Long employeeId, Long id) {
        departmentRepository.deleteEmployeeById(employeeId, id);
    }

    @Transactional
    public Department editDepartment(Department department, Long id) {
        Department departmentFromDB = findDepartmentById(id);
        String newPossibleName = department.getName();
        String newPossibleLocation = department.getLocation();
        if (newPossibleName != null) {
            departmentFromDB.setName(newPossibleName);
        }
        if (newPossibleLocation != null) {
            departmentFromDB.setLocation(newPossibleLocation);
        }
        return departmentFromDB;
    }
}
