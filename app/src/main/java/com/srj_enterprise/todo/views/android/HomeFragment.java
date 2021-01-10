package com.srj_enterprise.todo.views.android;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.srj_enterprise.todo.R;
import com.srj_enterprise.todo.listeners.DatePickerListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class HomeFragment extends Fragment implements View.OnClickListener, DatePickerListener {

    private TextView textViewDate;
    private DatePickerFragment datePickerFragment;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageViewCalendar = view.findViewById(R.id.image_view_calendar);
        imageViewCalendar.setOnClickListener(this);

        textViewDate = view.findViewById(R.id.text_view_date);
        textViewDate.setText(DateFormat.getDateInstance(DateFormat.LONG).format(Calendar.getInstance().getTime()));

        datePickerFragment = new DatePickerFragment();
        datePickerFragment.setOnDateSet(this);

        int[] images = new int[] { R.drawable.tasks_done_1, R.drawable.tasks_done_2, R.drawable.tasks_done_3, R.drawable.tasks_done_4};
        ImageView imageViewNoTask = view.findViewById(R.id.home_image_view_tasks_done);
        imageViewNoTask.setImageResource(images[new Random().nextInt(images.length)]);
    }

    @Override
    public void onClick(View v) {
        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(String date) {
        textViewDate.setText(date);
    }
}