package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskServices taskServices;

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskServices.insertTask(task);
    }

    @GetMapping
    public List<Task> listTasks(){
        return taskServices.listTasks();
    }

    @GetMapping("/{priority}")
    public List<Task> listTasksByPriority(@PathVariable String priority) {
        return taskServices.listTasksByPriority(priority);
    }

    @PutMapping("/{id}/move")
    public Task moveTask(@PathVariable int id){
        return taskServices.updateTaskProgress(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable int id){
        taskServices.deleteTask(id);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable int id, @RequestBody Task task){
        return taskServices.updateTask(id, task);
    }
}
