package com.meswright.ja.taskmanager.backend;

import com.meswright.ja.taskmanager.backend.entity.TaskEntity;
import com.meswright.ja.taskmanager.backend.repo.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class TaskManagerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(TaskRepository taskRepository) {
		return (String[] args) -> {
			TaskEntity task1 = new TaskEntity();
			task1.setId(UUID.randomUUID());
			task1.setTitle("Task 1");
			task1.setDescription("Task 1");
			task1.setStatus("status 1");
			taskRepository.save(task1);
			TaskEntity task2 = new TaskEntity();
			task2.setId(UUID.randomUUID());
			task2.setTitle("Task 2");
			task2.setDescription("Task 2");
			task2.setStatus("status 2");
			taskRepository.save(task2);
			TaskEntity task3 = new TaskEntity();
			task3.setId(UUID.randomUUID());
			task3.setTitle("Task 3");
			task3.setDescription("Task 3");
			task3.setStatus("status 3 From db");
			taskRepository.save(task3);
			taskRepository.findAll().forEach(System.out::println);
		};
	}
}
