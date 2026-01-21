package com.example.navigation_drawer_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View btnAddPen = view.findViewById(R.id.btnAgregarPen);
        View btnAddPencil = view.findViewById(R.id.btnAgregarPencil);
        View btnAddNotebook = view.findViewById(R.id.btnAgregarNotebook);
        View btnAddInk = view.findViewById(R.id.btnAgregarInk);
        View btnAddCita = view.findViewById(R.id.btnAgregarCita);

        btnAddPen.setOnClickListener(v -> goAdd("Pen"));
        btnAddPencil.setOnClickListener(v -> goAdd("Pencil"));
        btnAddNotebook.setOnClickListener(v -> goAdd("Notebook"));
        btnAddInk.setOnClickListener(v -> goAdd("Ink"));
        btnAddCita.setOnClickListener(v -> goAdd("Cita"));
    }

    private void goAdd(String tipo) {
        Bundle b = new Bundle();
        b.putString("tipo", tipo);
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_addItemFragment, b);
    }
}
