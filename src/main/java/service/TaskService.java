package service;

import model.Task;
import model.TaskStatus;
import org.springframework.stereotype.Service;
import repository.TaskRepository;

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

    public Task creatTask(Task task){

        if (task.getStatus() == null)task.setStatus(TaskStatus.PENDING);
        return repository.save(task);

    }

    public Task findTaskById(int id){
        Optional<Task> task = repository.findById(id);

        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateById(int id, Task update){

        Task task = findTaskById(id);

        task.setTitle(update.getTitle());
        task.setDescription(update.getDescription());
        task.setStatus(update.getStatus());

        return repository.save(task);

    }

    public void deletTaskById(int id){

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
