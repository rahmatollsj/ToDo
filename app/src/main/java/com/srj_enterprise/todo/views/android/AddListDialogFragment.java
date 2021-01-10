package com.srj_enterprise.todo.views.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.srj_enterprise.todo.R;
import com.srj_enterprise.todo.data.ColorData;
import com.srj_enterprise.todo.data.IconData;
import com.srj_enterprise.todo.enums.RecyclerViewAdapterType;
import com.srj_enterprise.todo.listeners.AddListDialogListener;
import com.srj_enterprise.todo.listeners.ItemOnClickListener;

public class AddListDialogFragment extends DialogFragment implements DialogInterface.OnShowListener, DialogInterface.OnClickListener, View.OnClickListener, ItemOnClickListener {

    private AddListDialogListener listener;
    private EditText editText;
    private Button buttonIcon;
    private Button buttonColor;
    private int selectedIconIndex;
    private IconData[] icons;
    private IconsRecyclerViewAdapter iconsRecyclerViewAdapter;
    private RecyclerView recyclerViewIcons;
    private int checkedColorIndex;
    private ColorData[] colors;
    private ColorsRecyclerViewAdapter colorsRecyclerViewAdapter;
    private RecyclerView recyclerViewColors;
    private AlertDialog alertDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_list, null);

        builder.setView(view)
                .setTitle(getString(R.string.new_list))
                .setPositiveButton(getText(R.string.save), this)
                .setNegativeButton(getText(R.string.cancel), null);

        editText = view.findViewById(R.id.edit_text_list_title);

        buttonIcon = view.findViewById(R.id.button_list_icon);
        buttonIcon.setOnClickListener(this);

        buttonColor = view.findViewById(R.id.button_list_color);
        buttonColor.setOnClickListener(this);

        initializeIconsRecyclerView(view);
        initializeColorsRecyclerView(view);

        alertDialog = builder.create();
        alertDialog.setOnShowListener(this);

        return alertDialog;
    }

    private void initializeIconsRecyclerView(View view) {
        selectedIconIndex = 0;
        icons = new IconData[] {
                new IconData(R.drawable.ic_circle, false),
                new IconData(R.drawable.ic_check, false),
                new IconData(R.drawable.ic_bulb, false),
                new IconData(R.drawable.ic_apartment, false),
                new IconData(R.drawable.ic_wrench, false),
                new IconData(R.drawable.ic_cup, false),
                new IconData(R.drawable.ic_location, false),
                new IconData(R.drawable.ic_person, false),
                new IconData(R.drawable.ic_camera, false),
                new IconData(R.drawable.ic_world, false),
                new IconData(R.drawable.ic_school, false),
                new IconData(R.drawable.ic_shopping_cart, false),
                new IconData(R.drawable.ic_game, false),
                new IconData(R.drawable.ic_sport, false),
                new IconData(R.drawable.ic_car, false),
                new IconData(R.drawable.ic_airplane, false),
                new IconData(R.drawable.ic_money, false),
                new IconData(R.drawable.ic_star, false)
        };
        icons[selectedIconIndex].setSelected(true);
        iconsRecyclerViewAdapter = new IconsRecyclerViewAdapter(getContext(), icons);
        iconsRecyclerViewAdapter.setOnClickListener(this);
        recyclerViewIcons = view.findViewById(R.id.recycler_view_list_icons);
        recyclerViewIcons.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewIcons.setAdapter(iconsRecyclerViewAdapter);
    }

    private void initializeColorsRecyclerView(View view) {
        checkedColorIndex = 0;
        int[] colorPickerColors = getResources().getIntArray(R.array.colorPickerColors);
        colors = new ColorData[colorPickerColors.length];
        for(int i = 0; i < colorPickerColors.length; i++) {
            colors[i] = new ColorData(colorPickerColors[i], false);
        }
        colors[checkedColorIndex].setChecked(true);
        colorsRecyclerViewAdapter = new ColorsRecyclerViewAdapter(colors);
        colorsRecyclerViewAdapter.setOnClickListener(this);
        recyclerViewColors = view.findViewById(R.id.recycler_view_list_colors);
        recyclerViewColors.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewColors.setAdapter(colorsRecyclerViewAdapter);
    }

    @Override
    public void onShow(DialogInterface dialog) {
        TypedValue color = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.colorSecondary, color, true);

        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(color.data);
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(color.data);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddListDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement AddListDialogListener");
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        listener.onDialogPositiveClick(editText.getText().toString(), icons[selectedIconIndex].getImageId(), colors[checkedColorIndex].getColor());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_list_icon:
                buttonIcon.setClickable(false);
                buttonIcon.setEnabled(false);
                buttonColor.setClickable(true);
                buttonColor.setEnabled(true);
                recyclerViewIcons.setVisibility(View.VISIBLE);
                recyclerViewColors.setVisibility(View.GONE);
                break;
            case R.id.button_list_color:
                buttonIcon.setClickable(true);
                buttonIcon.setEnabled(true);
                buttonColor.setClickable(false);
                buttonColor.setEnabled(false);
                recyclerViewIcons.setVisibility(View.GONE);
                recyclerViewColors.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onItemClick(RecyclerViewAdapterType type, int position) {
        switch (type) {
            case ICON:
                icons[selectedIconIndex].setSelected(false);
                icons[position].setSelected(true);
                selectedIconIndex = position;
                iconsRecyclerViewAdapter.notifyDataSetChanged();
                break;
            case COLOR:
                colors[checkedColorIndex].setChecked(false);
                colors[position].setChecked(true);
                checkedColorIndex = position;
                colorsRecyclerViewAdapter.notifyDataSetChanged();
                break;
        }
    }
}