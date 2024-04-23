package theara.servicetodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import theara.servicetodo.constant.ResponseDTO;
import theara.servicetodo.model.Todo;
import theara.servicetodo.model.TodoDto;
import theara.servicetodo.model.UpdateTodoDto;
import theara.servicetodo.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/all")
    public ResponseDTO findAllTodo() {
        return this.todoService.getAllTodo();
    }

    @GetMapping("/findById/{id}")
    public ResponseDTO findTodoOne(@PathVariable Long id) {
        return this.todoService.getTodoById(id);
    }

    @GetMapping("/find/{assignTo}")
    public List<Todo> getTodoByAssignTo(@PathVariable String assignTo) {
        return this.todoService.getTodoByAssignTo(assignTo);
    }

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody TodoDto todoDto) {
        return this.todoService.createTodo(todoDto);
    }

    @PutMapping("/update")
    public ResponseDTO update(@RequestBody UpdateTodoDto updateTodoDto) {
        return this.todoService.updateTodo(updateTodoDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO delete(@PathVariable Long id) {
        return this.todoService.deleteTodo(id);
    }
}
