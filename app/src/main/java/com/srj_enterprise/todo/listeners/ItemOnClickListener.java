package com.srj_enterprise.todo.listeners;

import com.srj_enterprise.todo.enums.RecyclerViewAdapterType;

public interface ItemOnClickListener {
    void onItemClick(RecyclerViewAdapterType type, int position);
}