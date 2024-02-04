package com.pockocmoc.task_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения Task Manager.
 */
@SpringBootApplication
public class TaskManagerApplication {

    /**
     * Метод запуска приложения.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

}
