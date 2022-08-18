package uz.pdp.springbootexample.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootexample.entity.Employee;
import uz.pdp.springbootexample.entity.EmployeeDto;
import uz.pdp.springbootexample.entity.Position;
import uz.pdp.springbootexample.service.EmployeeService;
import uz.pdp.springbootexample.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@Controller

public class EmployeController {

     private final PositionService positionService;
     private final EmployeeService employeeService;

    public EmployeController(PositionService positionService, EmployeeService employeeService) {
        this.positionService = positionService;
        this.employeeService = employeeService;
    }


    @ModelAttribute(name = "employee")
    public Employee getEmployee() {
        return new Employee();
    }

    @ModelAttribute(name = "employeeList")
    public List<Employee> getEmployeeList() {
        return employeeService.getAllEmployees();
    }

    @ModelAttribute(name = "positionList")
    public List<Position> getPositionList() {
        return positionService.getAllPositions();
    }


    @GetMapping("/employees")
    public String getAllEmployees() {  return "employee"; }


    @PostMapping("/employees")
    public String saveEmployee(@Valid EmployeeDto employeeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        employeeService.saveEmployee(employeeDto);
        return "employee";
    }


    @GetMapping("/employee-update/{id}")
    public String updateUserForm(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("positionList",positionService.getAllPositions());
        return "employee-update";
    }

    @PostMapping("/employee-update/{id}")
    public String updateEmployee(@Valid EmployeeDto employeeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }

        employeeService.updateEmployee(employeeDto);
        return "redirect:/employees";
    }










}
