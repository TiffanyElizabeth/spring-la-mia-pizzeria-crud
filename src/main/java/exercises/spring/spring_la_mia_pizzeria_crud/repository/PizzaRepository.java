package exercises.spring.spring_la_mia_pizzeria_crud.repository;

import java.util.List;
import exercises.spring.spring_la_mia_pizzeria_crud.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByNameContaining(String name);
}
