package theara.serviceuser.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import theara.serviceuser.model.TodoDto;

import java.util.List;

@FeignClient(value = "service-todo", url = "http://localhost:8088")
public interface ApiClient {
    @GetMapping("/todo/find/{assignTo}")
    List<TodoDto> getAllTodoByName(@PathVariable String assignTo);
}
