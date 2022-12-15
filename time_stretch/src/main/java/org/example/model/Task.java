package org.example.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TASKS")
public class Task {
    @Id
    @Column(name = "TASK_ID")
    private Integer taskId;
    @Column(name = "TASK_NAME")
    private String taskName;


    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task() {

    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}