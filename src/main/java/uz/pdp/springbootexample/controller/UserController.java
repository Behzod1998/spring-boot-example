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
@RequestMapping("/users")
public class UserController {


      @Value("please wake up")
      String propVal;

     private final PositionService positionService;
     private final UserService userService;

     private  final UserRepository UserRepository;

    public UserController(PositionService positionService, UserService userService, UserRepository UserRepository) {
        this.positionService = positionService;
        this.userService = userService;
        this.UserRepository = UserRepository;
    }

    @GetMapping("/info/{id}")
    public String eployeeAllInfoById(@PathVariable("id") Integer id, Model model){
        User user = userService.findById(id);
        UserDto userDto=new UserDto(
                user.getId(),user.getFullName(),user.getPosition().getId(),user.getSalary());
        model.addAttribute("user", userDto);

        return "user-info";
    }



    @ModelAttribute(name = "userDto")
    public UserDto getUserDto() {
        return new UserDto();
    }

    @ModelAttribute(name = "positionList")
    public List<Position> getPositionList() {
        return positionService.getAllPositions();
    }


    @GetMapping()
    public String getAllUsers(@RequestParam(defaultValue = "1") Integer page, Model model) {
        Page<UserListProjection>   allUsers  = userService.getAllUsers(page);
        model.addAttribute("currentPage", page);
        model.addAttribute("userList", allUsers);
        return "user";

    }



    @GetMapping("/get-form")
    public  String getFormUser(UserDto UserDto){return "user-form";}


    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Integer id, Model model){
        User user = userService.findById(id);
        UserDto userDto=new UserDto(
                user.getId(),user.getFullName(),user.getPosition().getId(),user.getSalary());
        model.addAttribute("user", userDto);

        model.addAttribute("positionList",positionService.getAllPositions());
        return "user-update";

    }

    @GetMapping("/user-delete/{id}")

    public String deleteUser(@PathVariable("id") Integer id , Model model){


        User user = userService.findById(id);

        userService.deleteUser(user);

        return "redirect:/users";

    }


    /**  ============== Post mapping lar boshi ===================*/

    @PostMapping
    public String saveUser(@Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user-form";
        }
        userService.saveUser(userDto);
//        model.addAttribute("UserList",UserService.getAllUsers());
        return "user";
    }


    @PostMapping("/user-update/{id}")
    public String updateUser(@Valid UserDto UserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }

        userService.updateUser(UserDto);
        return "redirect:/users";
    }


    @PostMapping("/search")
    public String searchUser(@RequestParam("usersName") String usersName ,Model model) {
          Integer page = 1;
        Page<UserListProjection>   searchUser  =   userService.findByUsername(usersName ,page);
        searchUser = searchUser.getSize()==0?null:searchUser;
        model.addAttribute("currentPage", page);
        model.addAttribute("userList", searchUser);
        return "user";
    }





}
