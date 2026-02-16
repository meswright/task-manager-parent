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

	// TODO: Remove and put under a development spring profile (or something).
	@Bean
	public CommandLineRunner run(TaskRepository taskRepository) {
		return (String[] args) -> {
			TaskEntity task1 = new TaskEntity();
			task1.setId(UUID.randomUUID());
			task1.setTitle("Email Finance");
			task1.setDescription("Chase finance about weekly earnings report");
			task1.setStatus("To Do");
			taskRepository.save(task1);
			TaskEntity task2 = new TaskEntity();
			task2.setId(UUID.randomUUID());
			task2.setTitle("Procure New Equipment");
			task2.setDescription("Replace years laptops.");
			task2.setStatus("In Progress");
			taskRepository.save(task2);
			TaskEntity task3 = new TaskEntity();
			task3.setId(UUID.randomUUID());
			task3.setTitle("Create App");
			task3.setDescription("Make app that tracks tasks.");
			task3.setStatus("Done");
			taskRepository.save(task3);
		};
	}
}
