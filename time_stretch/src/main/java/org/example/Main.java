package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import jakarta.persistence.Persistence;
import org.example.controller.Controller;


public class Main {
    public static void main(String[] args) {

        Persistence.createEntityManagerFactory("timestretch");
        var app = Javalin.create()
                .start(7071);

        app.get("/", ctx -> {
            ctx.render("template.html");

        });
        app.get("/task", Controller::listTasks);

    }
}