package com.meswright.ja.taskmanager.ui.view;

import com.meswright.ja.taskmanager.ui.dataprovider.UiDataProvider;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Named
@ViewScoped
@Slf4j
@RequiredArgsConstructor
public class HomeView {

    private final UiDataProvider dataProvider;

    @Getter
    private LocalDateTime time;

    @PostConstruct
    public void init() {
        time = dataProvider.test().time();
    }

}
