package com.srj_enterprise.todo.views.android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.srj_enterprise.todo.R;
import com.srj_enterprise.todo.controllers.MainController;
import com.srj_enterprise.todo.listeners.AddListDialogListener;
import com.srj_enterprise.todo.models.MainModel;
import com.srj_enterprise.todo.views.MainView;

public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener, AddListDialogListener {

    private static final String SELECTED_MENU_ITEM_ID = "SELECTED_MENU_ITEM_ID";

    private MainModel model;
    private Toolbar toolbar;
    private MainController controller;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private int selectedMenuItemId;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new MainModel();
        controller = new MainController(this, model);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        if(savedInstanceState == null) {
            MenuItem item = navigationView.getMenu().findItem(R.id.navigation_home);
            item.setChecked(true);
            selectedMenuItemId = item.getItemId();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, HomeFragment.class, null)
                    .setReorderingAllowed(true)
                    .commit();
        } else {
            selectedMenuItemId = savedInstanceState.getInt(SELECTED_MENU_ITEM_ID);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU_ITEM_ID, selectedMenuItemId);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void addList(int id, String title, int iconId, int color) {
        navigationView.getMenu()
                .add(MainModel.LISTS_GROUP_ID, id, MainModel.LISTS_ORDER, title)
                .setIcon(iconId)
                .setCheckable(true)
                .setChecked(true)
                .getIcon().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP));
        toolbar.setTitle(title);
        selectedMenuItemId = id;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, ListFragment.class, null)
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        toolbar.setTitle(menu.findItem(selectedMenuItemId).getTitle());
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.isCheckable()) {
            item.setChecked(true);
            toolbar.setTitle(item.getTitle());
            selectedMenuItemId = item.getItemId();
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, HomeFragment.class, null)
                        .setReorderingAllowed(true)
                        .commit();
                break;
            case R.id.navigation_motivation:
                break;
            case R.id.navigation_help:
                break;
            case R.id.navigation_add_list:
                new AddListDialogFragment().show(getSupportFragmentManager(), "add list dialog fragment");
                break;
            default:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, ListFragment.class, null)
                        .setReorderingAllowed(true)
                        .commit();
                break;
        }

        return true;
    }

    @Override
    public void onDialogPositiveClick(String listTitle, int iconId, int color) {
        controller.addList(listTitle, iconId, color);
    }
}