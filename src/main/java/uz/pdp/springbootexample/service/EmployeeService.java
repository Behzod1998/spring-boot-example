package uz.pdp.springbootexample.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.springbootexample.entity.*;
import uz.pdp.springbootexample.projection.EmployeeListProjection;
import uz.pdp.springbootexample.repository.EmployeeRepository;
import uz.pdp.springbootexample.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final PositionRepository positionRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    public void saveEmployee(EmployeeDto employeeDto) {

        Integer positionId = employeeDto.getPositionId();
        Optional<Position> optionalPosition = positionRepository.findById(positionId);
        if (optionalPosition.isEmpty()) {

            throw new IllegalStateException("Position not found ");

        }


        Employee employee = Employee
                .builder()
                .fullName(employeeDto.getFullName())
                .position(optionalPosition.get())
                .salary(employeeDto.getSalary())
                .build();

        employeeRepository.save(employee);

    }

    public Page<EmployeeListProjection> getAllEmployees(Integer page) {
        if (page < 1) {
            throw  new IllegalStateException("Bad req");

        }

        Pageable pageable = PageRequest.of(page-1,5, Sort.Direction.DESC,"updated_at");
        Page<EmployeeListProjection> all = employeeRepository.getAllEmployees(pageable);


        return all;
    }


    public Employee findById(Integer id){ return employeeRepository.getOne(id);
    }


    public void updateEmployee(EmployeeDto employeeDto) {


        Integer positionId = employeeDto.getPositionId();
        Optional<Position> optionalPosition = positionRepository.findById(positionId);
        if (optionalPosition.isEmpty()) {


            throw new IllegalStateException("Position not found ");

        }


        Employee employee = Employee
                .builder()
                .id(employeeDto.getId())
                .fullName(employeeDto.getFullName())
                .position(optionalPosition.get())
                .salary(employeeDto.getSalary())
                .build();

        employeeRepository.save(employee);


    }

    public void deleteEmploye(Employee employee) {

        employeeRepository.delete(employee);

    }





























}
