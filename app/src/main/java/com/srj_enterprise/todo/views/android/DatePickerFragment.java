package com.srj_enterprise.todo.views.android;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.srj_enterprise.todo.R;
import com.srj_enterprise.todo.listeners.DatePickerListener;

import java.text.DateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerListener listener;

    public void setOnDateSet(DatePickerListener listener) { this.listener = listener; }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        TypedValue positiveAndNegativeButtonColor = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.colorSecondary, positiveAndNegativeButtonColor, true);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        datePickerDialog.show();
        datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(positiveAndNegativeButtonColor.data);
        datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(positiveAndNegativeButtonColor.data);
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(listener != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            listener.onDateSet(DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime()));
        }
    }
}