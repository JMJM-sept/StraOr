package com.example.navigation_drawer_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fragment_pencil_list extends Fragment {

    private ItemAdapter adapter;

    public Fragment_pencil_list() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pencil_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v ->
                NavHostFragment.findNavController(Fragment_pencil_list.this).popBackStack()
        );

        RecyclerView rv = view.findViewById(R.id.rvPencil);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new ItemAdapter(new ItemAdapter.Listener() {
            @Override
            public void onEdit(Item item) {
                Bundle b = new Bundle();
                b.putString("tipo", "Pencil");
                b.putLong("id", item.id);

                NavHostFragment.findNavController(Fragment_pencil_list.this)
                        .navigate(R.id.addItemFragment, b);
            }

            @Override
            public void onDelete(Item item) {
                DataBase db = new DataBase(requireContext());
                db.deleteItem(item.id);
                adapter.setItems(db.getItemsByTipo("Pencil"));
            }
        });

        rv.setAdapter(adapter);

        DataBase db = new DataBase(requireContext());
        adapter.setItems(db.getItemsByTipo("Pencil"));

        FloatingActionButton fabAdd = view.findViewById(R.id.fabAddPencil);
        fabAdd.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("tipo", "Pencil");

            NavHostFragment.findNavController(Fragment_pencil_list.this)
                    .navigate(R.id.addItemFragment, bundle);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null) {
            DataBase db = new DataBase(requireContext());
            adapter.setItems(db.getItemsByTipo("Pencil"));
        }
    }
}
