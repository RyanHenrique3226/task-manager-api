package com.ryan.taskmanager.service;

import com.ryan.taskmanager.model.Task;
import com.ryan.taskmanager.model.TaskStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.ryan.taskmanager.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> allTasks(){
        return repository.findAll();
    }

    public Task createTask(Task task){

        if (task.getStatus() == null)task.setStatus(TaskStatus.PENDING);
        return repository.save(task);

    }

    public Task findTaskById(int id){
        Optional<Task> task = repository.findById(id);

        return task.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    public Task updateById(int id, Task update){

        Task task = findTaskById(id);

        task.setTitle(update.getTitle());
        task.setDescription(update.getDescription());
        task.setStatus(update.getStatus());

        return repository.save(task);

    }

    public void deleteTaskById(int id){

        Task task = findTaskById(id);
        repository.delete(task);

    }

    public Task updateStatus(int id, TaskStatus update){

        if (update != null) {
            Task task = findTaskById(id);

            task.setStatus(update);

            return repository.save(task);
        }

        throw new RuntimeException("Status cannot be null");

    }

}
