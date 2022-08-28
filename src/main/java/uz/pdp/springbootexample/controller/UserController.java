package uz.pdp.springbootexample.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootexample.entity.User;
import uz.pdp.springbootexample.entity.UserDto;
import uz.pdp.springbootexample.entity.Position;
import uz.pdp.springbootexample.projection.UserListProjection;
import uz.pdp.springbootexample.repository.UserRepository;
import uz.pdp.springbootexample.service.UserService;
import uz.pdp.springbootexample.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/Users")
public class UserController {


      @Value("please wake up")
      String propVal;

     private final PositionService positionService;
     private final UserService UserService;

     private  final UserRepository UserRepository;

    public UserController(PositionService positionService, UserService UserService, UserRepository UserRepository) {
        this.positionService = positionService;
        this.UserService = UserService;
        this.UserRepository = UserRepository;
    }

    @GetMapping("/info/{id}")
    public String eployeeAllInfoById(@PathVariable("id") Integer id, Model model){
        User user = UserService.findById(id);
        UserDto User=new UserDto(
                user.getId(),user.getFullName(),user.getPosition().getId(),user.getSalary());
        model.addAttribute("User", user);

        return "User-info";
    }



    @ModelAttribute(name = "UserDto")
    public UserDto getUserDto() {
        return new UserDto();
    }

    @ModelAttribute(name = "positionList")
    public List<Position> getPositionList() {
        return positionService.getAllPositions();
    }


    @GetMapping()
    public String getAllUsers(@RequestParam(defaultValue = "1") Integer page, Model model) {
        Page<UserListProjection>   allUsers  = UserService.getAllUsers(page);
        model.addAttribute("currentPage", page);
        model.addAttribute("UserList", allUsers);
        return "User";

    }



    @GetMapping("/get-form")
    public  String getFormUser(UserDto UserDto){return "User-form";}


    @GetMapping("/User-update/{id}")
    public String updateUserForm(@PathVariable("id") Integer id, Model model){
        User user = UserService.findById(id);
        UserDto User=new UserDto(
                user.getId(),user.getFullName(),user.getPosition().getId(),user.getSalary());
        model.addAttribute("User", User);

        model.addAttribute("positionList",positionService.getAllPositions());
        return "User-update";

    }

    @GetMapping("/User-delete/{id}")

    public String deleteUser(@PathVariable("id") Integer id , Model model){


        User User = UserService.findById(id);

        UserService.deleteUser(User);

        return "redirect:/Users";

    }


    /**  ============== Post mapping lar boshi ===================*/

    @PostMapping("/Users")
    public String saveUser(@Valid UserDto UserDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "User-form";
        }

        UserService.saveUser(UserDto);

//        model.addAttribute("UserList",UserService.getAllUsers());
        return "User";
    }















    @PostMapping("/User-update/{id}")
    public String updateUser(@Valid UserDto UserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }

        UserService.updateUser(UserDto);
        return "redirect:/Users";
    }


}
