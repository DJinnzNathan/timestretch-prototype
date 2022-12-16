package org.example.controller;


import io.javalin.http.Context;
import org.example.model.Repository;
import org.example.model.Task;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Controller {

    private Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }

    public void listTasks(Context ctx) {
        try {
            List<Task> emps = repository.findAll();
            if (ctx.headerMap().containsKey("Accept") && ctx.header("Accept").equals("application/json")) {

                ctx.json(emps);
            } else {
                ctx.render("template.html", Map.of("tasks", emps));
            }
        } catch (SQLException e) {
            ctx.render(e.getMessage());
            e.printStackTrace();
        }
    }

}
