package com.example.navigation_drawer_app;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View rowPen = view.findViewById(R.id.rowPen);
        View rowPencil = view.findViewById(R.id.rowPencil);
        View rowNotebook = view.findViewById(R.id.rowNotebook);
        View rowInk = view.findViewById(R.id.rowInk);

        if (rowPen != null) {
            rowPen.setOnClickListener(v ->
                    NavHostFragment.findNavController(ProfileFragment.this)
                            .navigate(R.id.penListFragment));
        }

        if (rowPencil != null) {
            rowPencil.setOnClickListener(v ->
                    NavHostFragment.findNavController(ProfileFragment.this)
                            .navigate(R.id.pencilListFragment));
        }

        if (rowNotebook != null) {
            rowNotebook.setOnClickListener(v ->
                    NavHostFragment.findNavController(ProfileFragment.this)
                            .navigate(R.id.notebookListFragment));
        }

        if (rowInk != null) {
            rowInk.setOnClickListener(v ->
                    NavHostFragment.findNavController(ProfileFragment.this)
                            .navigate(R.id.inkListFragment));
        }
    }
}
