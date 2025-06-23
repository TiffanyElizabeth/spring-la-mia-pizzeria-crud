package exercises.spring.spring_la_mia_pizzeria_crud.controller;

import exercises.spring.spring_la_mia_pizzeria_crud.model.Pizza;

import java.util.List;

import exercises.spring.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import exercises.spring.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = repository.findAll(); // SELECT * FROM `pizzas` and give me a list of pizza objects
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("hasPizzas", !pizzas.isEmpty());
        return "pizzas/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "/pizzas/detail";
    }
}
