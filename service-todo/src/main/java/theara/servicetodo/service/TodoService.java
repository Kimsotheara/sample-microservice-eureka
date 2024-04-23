package theara.servicetodo.service;

import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import theara.servicetodo.constant.ResponseDTO;
import theara.servicetodo.constant.Status;
import theara.servicetodo.model.Todo;
import theara.servicetodo.model.TodoDto;
import theara.servicetodo.model.UpdateTodoDto;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private ModelMapper mapper;

    public ResponseDTO getAllTodo() {
        try {
            List<Todo> todos = todoRepository.findAll();
            return new ResponseDTO("OK", todos);
        } catch (Exception e) {
            return new ResponseDTO("Failed to fetch todos", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO getTodoById(Long id) {
        var todo = todoRepository.getAllById(id);
        if (todo.isEmpty())
            return new ResponseDTO("Failed to fetch todo", Status.FAILED.value(), 500);
        return new ResponseDTO("OK", todo);

    }

    public List<Todo> getTodoByAssignTo(String assignTo) {
        List<Todo> list = this.todoRepository.getAssignTo(assignTo);
        if (list.isEmpty())
            throw new NotFoundException("AssignTo name not found");

        return list;
    }

    public ResponseDTO createTodo(TodoDto todoDto) {
        try {
            Todo todo = mapper.map(todoDto, Todo.class);
            todo = todoRepository.save(todo);
            return new ResponseDTO("Save OK", todo);
        } catch (Exception e) {
            return new ResponseDTO("Failed to create todo", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO updateTodo(UpdateTodoDto updateTodoDto){
        try{
            Optional<Todo> todoId = this.todoRepository.findById(updateTodoDto.getId());
            if (todoId.isEmpty())
                throw new NotFoundException("Todo id not found");
            Todo todo = mapper.map(updateTodoDto,Todo.class);
            todo = this.todoRepository.save(todo);
            return new ResponseDTO("Update Successfully",todo);
        }catch (Exception e){
            return new ResponseDTO("Failed to update todo", Status.FAILED.value(), 500);
        }
    }

    public ResponseDTO deleteTodo(Long id){
        Optional<Todo> todoId = this.todoRepository.findById(id);
        if (todoId.isEmpty())
            throw new NotFoundException("Todo id not found");
        this.todoRepository.deleteById(id);
        return new ResponseDTO("delete OK");
    }
}
