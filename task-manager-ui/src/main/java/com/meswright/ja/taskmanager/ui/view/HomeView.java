package com.meswright.ja.taskmanager.ui.view;

import com.meswright.ja.taskmanager.ui.dataprovider.UiDataProvider;
import com.meswright.ja.taskmanager.ui.pojo.TaskUio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
@ViewScoped
@Slf4j
@RequiredArgsConstructor
public class HomeView implements Serializable {

    @Serial
    private static final long serialVersionUID = -2100469738248120149L;

    private final UiDataProvider dataProvider;

    @Getter
    private List<TaskUio> tasks;

    @Getter
    @Setter
    private TaskUio selectedTask;

    @Setter
    @Getter
    private List<TaskUio> selectedTasks;

    @PostConstruct
    public void init() {
        tasks = dataProvider.getTasks();
        selectedTasks = new ArrayList<>();
    }

    public void openNew() {
        selectedTask = new TaskUio();
        selectedTask.setId(UUID.randomUUID());
    }

    public void saveTask() {
        if (!tasks.contains(selectedTask)) {
            tasks.add(selectedTask);
            dataProvider.createTask(selectedTask);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task Added"));
        } else {
            dataProvider.updateTask(selectedTask);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task Updated"));
        }
        PrimeFaces.current().executeScript("PF('manageTaskDialog').hide()");
        PrimeFaces.current().ajax().update("tasks-form:messages", "tasks-form:dt-tasks");
    }

    public void deleteTask() {
        tasks.remove(selectedTask);
        selectedTasks.remove(selectedTask);
        dataProvider.deleteTask(selectedTask);
        selectedTask = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Task Removed"));
        PrimeFaces.current().ajax().update("tasks-form:messages", "tasks-form:dt-tasks");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedTasks()) {
            int size = selectedTasks.size();
            return size > 1 ? size + " tasks selected" : "1 task selected";
        }
        return "Delete";
    }

    public boolean hasSelectedTasks() {
        return selectedTasks != null && !selectedTasks.isEmpty();
    }

    public void deleteSelectedTasks() {
        tasks.removeAll(selectedTasks);
        dataProvider.deleteTasks(selectedTasks);
        selectedTasks = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tasks Removed"));
        PrimeFaces.current().ajax().update("tasks-form:messages", "tasks-form:dt-tasks");
        PrimeFaces.current().executeScript("PF('dtTasks').clearFilters()");
    }

}
