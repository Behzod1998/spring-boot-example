package uz.pdp.springbootexample.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootexample.entity.Employee;
import uz.pdp.springbootexample.entity.EmployeeDto;
import uz.pdp.springbootexample.entity.Position;
import uz.pdp.springbootexample.projection.EmployeeListProjection;
import uz.pdp.springbootexample.repository.EmployeeRepository;
import uz.pdp.springbootexample.service.EmployeeService;
import uz.pdp.springbootexample.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeController {


      @Value("please wake up")
      String propVal;

     private final PositionService positionService;
     private final EmployeeService employeeService;

     private  final EmployeeRepository employeeRepository;

    public EmployeController(PositionService positionService, EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.positionService = positionService;
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }


    @ModelAttribute(name = "employeeDto")
    public EmployeeDto getEmployeeDto() {
        return new EmployeeDto();
    }

    @ModelAttribute(name = "positionList")
    public List<Position> getPositionList() {
        return positionService.getAllPositions();
    }


    @GetMapping()
    public String getAllEmployees(@RequestParam(defaultValue = "1") Integer page, Model model) {
        Page<EmployeeListProjection>   allEmployees  = employeeService.getAllEmployees(page);
        model.addAttribute("currentPage", page);
        model.addAttribute("employeeList", allEmployees);
        return "employee";

    }

    @GetMapping("/info/{id}")
   public String eployeeAllInfoById(@PathVariable("id") Integer id, Model model){
        model.addAttribute("employee", "employee olip o'rniga keyinroq  beramaman");
        return "employee-info";
   }


    @GetMapping("/get-form")
    public  String getFormEmployee(EmployeeDto employeeDto){return "employee-form";}


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
