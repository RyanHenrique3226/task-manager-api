package com.ryan.taskmanager.controller;

import com.ryan.taskmanager.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task create = service.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id){
        Task task = service.findTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable int id, @RequestBody Task task){
        Task update = service.updateById(id, task);
        return ResponseEntity.ok(update);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable int id, @RequestBody Task task){
        Task update = service.updateStatus(id, task.getStatus());
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id){
         service.deleteTaskById(id);
    }

}
