package com.example.navigation_drawer_app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout cajonDeNavegacion;
    private NavigationView vistaDeNavegacion;
    private MaterialToolbar barraDeHerramientas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cajonDeNavegacion = findViewById(R.id.drawer_layout);
        vistaDeNavegacion = findViewById(R.id.nav_view);
        barraDeHerramientas = findViewById(R.id.top_app_bar);

        setSupportActionBar(barraDeHerramientas);

        ActionBarDrawerToggle alternador = new ActionBarDrawerToggle(
                this, cajonDeNavegacion, barraDeHerramientas,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        cajonDeNavegacion.addDrawerListener(alternador);
        alternador.syncState();

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(vistaDeNavegacion, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, cajonDeNavegacion);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        return NavigationUI.navigateUp(navController, cajonDeNavegacion) || super.onSupportNavigateUp();
    }

    @SuppressLint("GestureBackNavigation")
    @Override
    public void onBackPressed() {
        if (cajonDeNavegacion.isDrawerOpen(GravityCompat.START)) {
            cajonDeNavegacion.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}