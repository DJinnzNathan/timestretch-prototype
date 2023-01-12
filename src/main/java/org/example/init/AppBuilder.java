package org.example.init;

import org.example.controller.Controller;
import org.example.model.Repository;

public class AppBuilder {
    public static Controller build() {
        Repository repo = AppFactory.createRepository();
        Controller controller = AppFactory.createController(repo);
        return controller;
    }
}
