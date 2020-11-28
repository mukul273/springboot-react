package com.springframework.react.controller;

import com.springframework.react.exception.ResourceNotFoundException;
import com.springframework.react.model.Employee;
import com.springframework.react.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employeeById = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee "+ id +" not Found")
        );
        return ResponseEntity.ok(employeeById);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {

        Employee updateEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee " + id + " not Found")
        );

        updateEmployee.setFirstName(employee.getFirstName());
        updateEmployee.setLastName(employee.getLastName());
        updateEmployee.setEmailId(employee.getEmailId());
        Employee updatedEmployee = employeeRepository.saveAndFlush(updateEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/deleteemp/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        Employee deleteEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee " + id + " not Found")
        );
        employeeRepository.deleteById(deleteEmployee.getId());

        Map<String, Boolean> deletedEmployee = new HashMap<>();
        deletedEmployee.put("Deleted", true);

        return ResponseEntity.ok(deletedEmployee)   ;
    }
}
