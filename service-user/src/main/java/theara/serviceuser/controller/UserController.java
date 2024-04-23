package theara.serviceuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import theara.serviceuser.constant.ResponseDTO;
import theara.serviceuser.model.UserDto;
import theara.serviceuser.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseDTO findAllUser(){
        return this.userService.getAllUser();
    }

    @GetMapping("/find/{username}")
    public ResponseDTO findUserAndTodo(@PathVariable String username){
        return this.userService.getUserAndTodo(username);
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody UserDto userDto){
        return this.userService.createUser(userDto);
    }
}
