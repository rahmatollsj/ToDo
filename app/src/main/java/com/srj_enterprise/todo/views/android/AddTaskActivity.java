package com.srj_enterprise.todo.views.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.srj_enterprise.todo.R;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        TextView textViewSave = findViewById(R.id.text_view_save);
        textViewSave.setOnClickListener(this);

        TextView textViewCancel = findViewById(R.id.text_view_cancel);
        textViewCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.text_view_save) {

        }

        Intent intent = new Intent(this, MainActivity.class);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}