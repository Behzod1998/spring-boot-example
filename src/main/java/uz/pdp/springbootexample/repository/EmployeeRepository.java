package uz.pdp.springbootexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootexample.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
