package com.ryan.taskmanager.controller;

import com.ryan.taskmanager.model.Task;
import org.springframework.web.bind.annotation.*;
import com.ryan.taskmanager.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return service.allTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return service.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id){
        return service.findTaskById(id);
    }

    @PutMapping("/{id}")
    public Task updateTaskById(@PathVariable int id, @RequestBody Task task){
        return service.updateById(id, task);
    }

    @PutMapping("/{id}/status")
    public Task updateStatus(@PathVariable int id, @RequestBody Task task){
        return service.updateStatus(id, task.getStatus());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
         service.deleteTaskById(id);
    }

}
