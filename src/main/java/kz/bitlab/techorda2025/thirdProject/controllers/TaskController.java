/*
package kz.bitlab.techorda2025.thirdProject.controllers;

import kz.bitlab.techorda2025.thirdProject.db.Task;
import kz.bitlab.techorda2025.thirdProject.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }
    @GetMapping("/addTask")
    public String showAddTaskForm(Model model) {
        return "addTask";
    }

    @PostMapping("/addTask")
    public String addTask(@RequestParam(name = "taskName") String taskname,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "date") String date,
                          @RequestParam(name = "complete") String complete) {

        Task task = Task.builder()
                .name(taskname)
                .description(description)
                .deadlineDate(date)
                .isCompleted(Boolean.parseBoolean(complete))
                .build();

        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/details/{idshka}")
    public String getDetailsOfTask(Model model,
                           @PathVariable("idshka") Long id) {
        Task task = new Task();
        if(taskRepository.findById(id).isPresent()) {
            task = taskRepository.findById(id).get();
        } else{
            task.setId(id);
            task.setName("Default Task");
            task.setDescription("Default Description");
            task.setDeadlineDate(null);
            task.setCompleted(false);
        }
        model.addAttribute("task", task);
        return "details";
    }

    @PostMapping("/saveTask")
    public String saveTask(   @RequestParam(name = "id") Long id,
                              @RequestParam(name = "taskName") String taskname,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "date") String date,
                              @RequestParam(name = "complete") String complete) {

        Task task = Task.builder()
                .id(id)
                .name(taskname)
                .description(description)
                .deadlineDate(date)
                .isCompleted(Boolean.parseBoolean(complete))
                .build();

        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @DeleteMapping(value = "/deleteTask")
    public String deleteTask(@RequestParam(name = "id") Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    @PostMapping(value="/search")
    public String search(@RequestParam(name="searchValue") String searchValue, Model model) {
        List<Task> taskList = taskRepository.findAllByNameContainsIgnoreCase(searchValue);
        model.addAttribute("tasks", taskList);
        return "tasks";
    }
}
*/
