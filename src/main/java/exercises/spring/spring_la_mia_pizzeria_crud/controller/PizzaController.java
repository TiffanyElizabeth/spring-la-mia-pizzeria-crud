package exercises.spring.spring_la_mia_pizzeria_crud.controller;

import exercises.spring.spring_la_mia_pizzeria_crud.model.Pizza;

import java.util.List;

import exercises.spring.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import exercises.spring.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

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
        return "/pizzas/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "/pizzas/detail";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = repository.findByNameContainingIgnoreCase(name);
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("hasPizzas", !pizzas.isEmpty());
        return "/pizzas/index";
    }

    // @GetMapping("/search")
    // public String search(@RequestParam(name = "query") String query, Model model)
    // {
    // List<Pizza> pizzas =
    // repository.findByNameOrDescriptionContainingIgnoreCase(query, query);
    // model.addAttribute("pizzas", pizzas);
    // model.addAttribute("hasPizzas", !pizzas.isEmpty());
    // return "/pizzas/index";
    // } DOESN'T WORK BECAUSE OF @LOB in DESCRIPTION

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzas/create";
        }

        repository.save(formPizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "/pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzas/edit";
        }

        repository.save(formPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/pizzas";
    }

}
