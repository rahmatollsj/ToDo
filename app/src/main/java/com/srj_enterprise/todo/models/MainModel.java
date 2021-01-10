package com.srj_enterprise.todo.models;

import com.srj_enterprise.todo.R;
import com.srj_enterprise.todo.data.ListData;

import java.util.ArrayList;

public class MainModel {
    public static final int LISTS_GROUP_ID = R.id.group_lists;
    public static final int LISTS_ORDER = 2;

    private ArrayList<ListData> lists;
    private int listId;

    public int getListId(int index) { return lists.get(index).getId(); }
    public int getListsArraySize() { return lists.size(); }

    public MainModel() {
        lists = new ArrayList<>();
        listId = 0;
    }

    public void addList(String title, int iconId, int color) {
        lists.add(new ListData(listId, title, iconId, color));
        listId++;
    }
}