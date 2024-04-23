package theara.serviceuser.service;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theara.serviceuser.FeignClient.ApiClient;
import theara.serviceuser.constant.ResponseDTO;
import theara.serviceuser.constant.Status;
import theara.serviceuser.model.ResponseAll;
import theara.serviceuser.model.TodoDto;
import theara.serviceuser.model.User;
import theara.serviceuser.model.UserDto;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ApiClient apiClient;
    @Autowired
    private ModelMapper mapper;

    public ResponseDTO getAllUser() {
        try {
            List<User> userList = this.userRepository.findAll();
            return new ResponseDTO("OK", userList);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch users", Status.FAILED.value(), 500);
        }

    }

    public ResponseDTO getUserAndTodo(String username) {

        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            return new ResponseDTO("User not found", Status.NOT_FOUND.value(), 404);
        }

        List<TodoDto> todoOpt;
        try {
            todoOpt = this.apiClient.getAllTodoByName(username);
        } catch (FeignException e) {
            return new ResponseDTO("Failed to retrieve todos for username: " + username, Status.FAILED.value(), 500);
        }

        if (todoOpt == null || todoOpt.isEmpty()) {
            return new ResponseDTO("Todos not found for username: " + username, Status.NOT_FOUND.value(), 404);
        }
        ResponseAll responseAll = new ResponseAll();
        responseAll.setUsername(user.getUsername());
        responseAll.setPosition(user.getPosition());
        responseAll.setTodoDto(todoOpt);
        return new ResponseDTO("User and Todos retrieved successfully", Status.SUCCESS.value(), 200, responseAll);

    }

    public ResponseDTO createUser(UserDto userDto) {
        try {
            User user = mapper.map(userDto, User.class);
            user = this.userRepository.save(user);
            return new ResponseDTO("save OK", user);
        } catch (Exception e) {
            return new ResponseDTO("Failed to create user", Status.FAILED.value(), 500);
        }
    }
}
