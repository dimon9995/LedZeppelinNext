package com.example.springdata;

import com.example.springdata.service.UserService;
import org.springframework.boot.CommandLineRunner;

public class DemoRunner implements CommandLineRunner {
    private final UserService userService;

    public DemoRunner(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void run(String... args) throws Exception {
        userService.createUser("john_doe", "john@example.com");
        userService.createUser("jane_doe", "jane@example.com");
        System.out.println("Пользователи добавлены");
    }
}
