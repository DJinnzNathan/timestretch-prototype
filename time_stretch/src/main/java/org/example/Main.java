package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    public static void main(String[] args) {

        var app = Javalin.create()
                .start(7071);

        app.get("/tasks", ctx -> {
            ctx.render("template.html");
        });

        app._conf.addStaticFiles("stat", Location.CLASSPATH);






    }
}