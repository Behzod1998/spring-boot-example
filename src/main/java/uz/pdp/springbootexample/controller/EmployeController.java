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

//    @ModelAttribute(name = "employeeList")
//    public List<Employee> getEmployeeList() {
//        return employeeService.getAllEmployees();
//    }

    @ModelAttribute(name = "positionList")
    public List<Position> getPositionList() {
        return positionService.getAllPositions();
    }


    @GetMapping("/employees")
    public String getAllEmployees(@RequestParam("page") Integer page, Model model) {

        model.addAttribute("employeeList",employeeService.getAllEmployees(page));

        return "employee";

    }



    @GetMapping("/employee-form")
    public  String getFormEmployee(EmployeeDto employeeDto){

        return "employee-form";

    }


    @GetMapping("/employee-update/{id}")
    public String updateUserForm(@PathVariable("id") Integer id, Model model){
        Employee employe = employeeService.findById(id);
        EmployeeDto employee=new EmployeeDto(
                employe.getId(),employe.getFullName(),employe.getPosition().getId(),employe.getSalary());
        model.addAttribute("employee", employee);

        model.addAttribute("positionList",positionService.getAllPositions());
        return "employee-update";

    }

    @GetMapping("/employee-delete/{id}")

    public String deleteEmploye(@PathVariable("id") Integer id , Model model){


        Employee employee = employeeService.findById(id);

        employeeService.deleteEmploye(employee);

        return "redirect:/employees";

    }


    /**  ============== Post mapping lar boshi ===================*/

    @PostMapping("/employees")
    public String saveEmployee(@Valid EmployeeDto employeeDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "employee-form";
        }

        employeeService.saveEmployee(employeeDto);

//        model.addAttribute("employeeList",employeeService.getAllEmployees());
        return "employee";
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
