package theara.servicetodo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import theara.servicetodo.model.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    @Query(value = "select * from todo where id=?1",nativeQuery = true)
    List<Todo> getAllById(Long id);
    @Query(value = "select * from todo where assign_to=?1",nativeQuery = true)
    List<Todo> getAssignTo(String assign_to);
}
