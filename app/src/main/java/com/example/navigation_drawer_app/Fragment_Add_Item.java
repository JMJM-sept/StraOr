package com.example.navigation_drawer_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class Fragment_Add_Item extends Fragment {

    public Fragment_Add_Item() {
        super(R.layout.fragment_add_item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvTitulo = view.findViewById(R.id.tvTitulo);

        EditText etBrand = view.findViewById(R.id.etBrand);
        EditText etUse = view.findViewById(R.id.etUse);
        EditText etColor = view.findViewById(R.id.etColor);

        EditText etInkColor = view.findViewById(R.id.etInkColor);
        EditText etType = view.findViewById(R.id.etType);

        EditText etHojas = view.findViewById(R.id.etHojas);
        EditText etGraduacion = view.findViewById(R.id.etGraduacion);
        EditText etMililitros = view.findViewById(R.id.etMililitros);

        Button btnGuardar = view.findViewById(R.id.btnGuardar);
        Button btnGoBack = view.findViewById(R.id.btnRegresar);

        btnGoBack.setOnClickListener(v ->
                NavHostFragment.findNavController(Fragment_Add_Item.this).popBackStack()
        );

        etInkColor.setVisibility(View.GONE);
        etType.setVisibility(View.GONE);
        etHojas.setVisibility(View.GONE);
        etGraduacion.setVisibility(View.GONE);
        etMililitros.setVisibility(View.GONE);

        Bundle args = getArguments();
        String tipo = args != null ? args.getString("tipo", "") : "";
        long editId = (args != null && args.containsKey("id")) ? args.getLong("id", -1) : -1;
        boolean isEdit = editId != -1;

        if ("Cita".equals(tipo)) {
            tvTitulo.setText(isEdit ? "Editar cita" : "Registrar cita");
            etBrand.setHint("Título");
            etUse.setHint("Fecha");
            etColor.setHint("Lugar");
        } else {
            tvTitulo.setText((isEdit ? "Edit " : "Add ") + tipo);
            etBrand.setHint("Brand");
            etUse.setHint("Use");
            etColor.setHint("Color");
        }

        switch (tipo) {
            case "Pen":
                etInkColor.setVisibility(View.VISIBLE);
                etType.setVisibility(View.VISIBLE);
                break;
            case "Notebook":
                etHojas.setVisibility(View.VISIBLE);
                break;
            case "Pencil":
                etGraduacion.setVisibility(View.VISIBLE);
                break;
            case "Ink":
                etMililitros.setVisibility(View.VISIBLE);
                break;
        }

        if (isEdit) {
            DataBase db = new DataBase(requireContext());
            Item it = db.getItemById(editId);

            if (it == null) {
                Toast.makeText(requireContext(), "Item not found", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(this).popBackStack();
                return;
            }

            etBrand.setText(it.brand != null ? it.brand : "");
            etUse.setText(it.useText != null ? it.useText : "");
            etColor.setText(it.color != null ? it.color : "");

            if ("Pen".equals(tipo)) {
                etInkColor.setText(it.inkColor != null ? it.inkColor : "");
                etType.setText(it.typePen != null ? it.typePen : "");
            } else if ("Notebook".equals(tipo)) {
                etHojas.setText(it.noSheets != null ? String.valueOf(it.noSheets) : "");
            } else if ("Ink".equals(tipo)) {
                etMililitros.setText(it.quantity != null ? String.valueOf(it.quantity) : "");
            } else if ("Pencil".equals(tipo)) {
                etGraduacion.setText(it.typeGraph != null ? it.typeGraph : "");
            }
        }

        btnGuardar.setOnClickListener(v -> {

            btnGuardar.setEnabled(false);

            String brand = etBrand.getText().toString().trim();
            String useText = etUse.getText().toString().trim();
            String color = etColor.getText().toString().trim();

            if (brand.isEmpty()) {
                etBrand.setError("Required");
                btnGuardar.setEnabled(true);
                return;
            }

            if ("Cita".equals(tipo)) {
                if (useText.isEmpty()) {
                    etUse.setError("Required");
                    btnGuardar.setEnabled(true);
                    return;
                }
                if (color.isEmpty()) {
                    etColor.setError("Required");
                    btnGuardar.setEnabled(true);
                    return;
                }
            }

            String inkColor = null;
            String typePen = null;
            Integer noSheets = null;
            Integer quantity = null;
            String typeGraph = null;

            switch (tipo) {
                case "Pen": {
                    inkColor = etInkColor.getText().toString().trim();
                    typePen = etType.getText().toString().trim();
                    if (inkColor.isEmpty()) { etInkColor.setError("Required"); btnGuardar.setEnabled(true); return; }
                    if (typePen.isEmpty()) { etType.setError("Required"); btnGuardar.setEnabled(true); return; }
                    break;
                }
                case "Notebook": {
                    String sheetsTxt = etHojas.getText().toString().trim();
                    if (sheetsTxt.isEmpty()) { etHojas.setError("Required"); btnGuardar.setEnabled(true); return; }
                    try { noSheets = Integer.parseInt(sheetsTxt); }
                    catch (NumberFormatException e) { etHojas.setError("Invalid number"); btnGuardar.setEnabled(true); return; }
                    break;
                }
                case "Ink": {
                    String qtyTxt = etMililitros.getText().toString().trim();
                    if (qtyTxt.isEmpty()) { etMililitros.setError("Required"); btnGuardar.setEnabled(true); return; }
                    try { quantity = Integer.parseInt(qtyTxt); }
                    catch (NumberFormatException e) { etMililitros.setError("Invalid number"); btnGuardar.setEnabled(true); return; }
                    break;
                }
                case "Pencil": {
                    typeGraph = etGraduacion.getText().toString().trim();
                    if (typeGraph.isEmpty()) { etGraduacion.setError("Required"); btnGuardar.setEnabled(true); return; }
                    break;
                }
            }

            DataBase db = new DataBase(requireContext());

            if (isEdit) {
                int rows = db.updateItem(
                        editId,
                        tipo,
                        brand,
                        useText,
                        color,
                        ("Pen".equals(tipo) ? inkColor : null),
                        ("Pen".equals(tipo) ? typePen : null),
                        ("Notebook".equals(tipo) ? noSheets : null),
                        ("Ink".equals(tipo) ? quantity : null),
                        ("Pencil".equals(tipo) ? typeGraph : null)
                );

                if (rows <= 0) {
                    Toast.makeText(requireContext(), "Error updating", Toast.LENGTH_SHORT).show();
                    btnGuardar.setEnabled(true);
                } else {
                    Toast.makeText(requireContext(), "Updated!", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(Fragment_Add_Item.this).popBackStack();
                }

            } else {
                long id = db.insertItem(
                        tipo,
                        brand,
                        useText,
                        color,
                        inkColor,
                        typePen,
                        noSheets,
                        quantity,
                        typeGraph
                );

                if (id == -1) {
                    Toast.makeText(requireContext(), "Error saving", Toast.LENGTH_SHORT).show();
                    btnGuardar.setEnabled(true);
                } else {
                    Toast.makeText(requireContext(), "Saved!", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(Fragment_Add_Item.this).popBackStack();
                }
            }
        });
    }
}
