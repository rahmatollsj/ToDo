package com.srj_enterprise.todo.controllers;

import com.srj_enterprise.todo.models.MainModel;
import com.srj_enterprise.todo.views.MainView;

public class MainController {
    MainView view;
    MainModel model;

    public MainController(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void addList(String title, int iconId, int color) {
        model.addList(title, iconId, color);
        view.addList(model.getListId(model.getListsArraySize() - 1), title, iconId, color);
    }
}