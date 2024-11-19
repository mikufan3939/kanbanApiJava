package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServices {

    @Autowired
    private TaskRepository taskRepository;

    public Task insertTask(Task task){
        try{
            return taskRepository.save(task);
        }
        catch (Exception e){
            throw new RuntimeException("Erro ao inserir task");
        }

    }

    public List<Task> listTasks(){
        return taskRepository.findByOrderByStatusAscPriorityAsc();
    }

    public List<Task> listTasksByPriority(String priority){
        return taskRepository.findByPriority(priority);
    }

    public List<Task> listOverdueTasks(){
        LocalDate currentDate = LocalDate.now();
        return taskRepository.findExpiredTasksWithIncompleteStatus(currentDate);
    }

    public Task selectTaskById(int id){
        Optional<Task> task=taskRepository.findById(id);
        if(task.isPresent()){
            return task.get();
        }
        throw new RuntimeException("Task nao encontrada");
    }

    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }

    public Task updateTaskProgress(int id){
        Task task=selectTaskById(id);
        if(task.getStatus().equals("A fazer")){
            task.setStatus("Em progresso");
        }
        else if(task.getStatus().equals("Em progresso")){
            task.setStatus("Concluido");
        }
        return taskRepository.save(task);
    }

    public Task updateTask(int id, Task task){
        Task selectedTask=selectTaskById(id);
        selectedTask.setTitle(task.getTitle());
        selectedTask.setDescription(task.getDescription());
        selectedTask.setPriority(task.getPriority());
        return taskRepository.save(selectedTask);
    }
}
