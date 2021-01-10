package com.srj_enterprise.todo.views.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.srj_enterprise.todo.R;

import java.util.Random;

public class ListFragment extends Fragment implements ActivityResultCallback<ActivityResult>, View.OnClickListener {

    private ActivityResultLauncher<Intent> activityResultLauncher;

    public ListFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this);

        int[] images = new int[] { R.drawable.tasks_done_1, R.drawable.tasks_done_2, R.drawable.tasks_done_3, R.drawable.tasks_done_4};
        ImageView imageViewNoTask = view.findViewById(R.id.list_image_view_task_done);
        imageViewNoTask.setImageResource(images[new Random().nextInt(images.length)]);

        FloatingActionButton floatingActionButtonAddTask = view.findViewById(R.id.floating_action_button_add_task);
        floatingActionButtonAddTask.setOnClickListener(this);
    }


    @Override
    public void onActivityResult(ActivityResult result) {
        if(result.getResultCode() == Activity.RESULT_OK) {
            Intent intent = result.getData();
            addTask();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), AddTaskActivity.class);
        activityResultLauncher.launch(intent);
    }

    private void addTask() {

    }
}