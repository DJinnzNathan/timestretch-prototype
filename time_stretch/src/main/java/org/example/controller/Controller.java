package org.example.controller;


import io.javalin.http.Context;
import org.example.model.Repository;
import org.example.model.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Controller {
    public static void listTasks(Context ctx) {
        try {
            List<Task> tasks = Repository.getInstance().findAll();
            ctx.render("template.html", Map.of("tasks", tasks));
        } catch (SQLException e) {
            ctx.render(e.getMessage());
            e.printStackTrace();
        }
    }
}
