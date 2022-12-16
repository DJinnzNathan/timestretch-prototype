package org.example.model;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
@Table(name = "protocols")
public class Protocol {
    @Id
    private Integer id;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToOne
    @JoinColumn(nullable = false, name = "TASK_ID", referencedColumnName = "TASK_ID")
    private Task task;

    public Protocol() {

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Protocol(Date startDate, Date endDate, Task task) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.task = task;
    }
}
