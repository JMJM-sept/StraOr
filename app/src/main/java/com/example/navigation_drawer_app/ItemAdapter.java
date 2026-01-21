package com.example.navigation_drawer_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.VH> {

    public interface Listener {
        void onEdit(Item item);
        void onDelete(Item item);
    }

    private final List<Item> items = new ArrayList<>();
    private final Listener listener;

    public ItemAdapter(@NonNull Listener listener) {
        this.listener = listener;
    }

    public void setItems(List<Item> list) {
        items.clear();
        if (list != null) items.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_with_buttons, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Item it = items.get(position);

        String tipo = it.tipo != null ? it.tipo : "";

        String title = it.brand != null ? it.brand : "";
        if (!"Cita".equals(tipo) && it.color != null && !it.color.trim().isEmpty()) {
            title += " (" + it.color.trim() + ")";
        }
        h.tvTitle.setText(title);

        String use = it.useText != null ? it.useText.trim() : "";
        h.tvUse.setText(getUseLabel(tipo) + use);

        String extra = buildExtra(it);
        if (extra == null || extra.trim().isEmpty()) {
            h.tvExtra.setVisibility(View.GONE);
        } else {
            h.tvExtra.setVisibility(View.VISIBLE);
            h.tvExtra.setText(extra);
        }

        h.btnEdit.setOnClickListener(v -> listener.onEdit(it));
        h.btnDelete.setOnClickListener(v -> listener.onDelete(it));
    }

    private String getUseLabel(String tipo) {
        if ("Cita".equals(tipo)) {
            return "Fecha: ";
        }
        return "Use: ";
    }

    private String buildExtra(Item it) {
        String tipo = it.tipo != null ? it.tipo : "";

        if ("Pen".equals(tipo)) {
            String a = (it.inkColor != null && !it.inkColor.trim().isEmpty()) ? "Ink: " + it.inkColor.trim() : "";
            String b = (it.typePen != null && !it.typePen.trim().isEmpty()) ? "Type: " + it.typePen.trim() : "";
            if (!a.isEmpty() && !b.isEmpty()) return a + " • " + b;
            return (a + b).trim();
        } else if ("Notebook".equals(tipo)) {
            return it.noSheets != null ? "Sheets: " + it.noSheets : "";
        } else if ("Ink".equals(tipo)) {
            return it.quantity != null ? "Quantity: " + it.quantity : "";
        } else if ("Pencil".equals(tipo)) {
            return it.typeGraph != null && !it.typeGraph.trim().isEmpty()
                    ? "Type: " + it.typeGraph.trim()
                    : "";
        } else if ("Cita".equals(tipo)) {
            return it.color != null && !it.color.trim().isEmpty()
                    ? "Lugar: " + it.color.trim()
                    : "";
        }

        return "";
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvUse, tvExtra;
        MaterialButton btnEdit, btnDelete;

        VH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvUse = itemView.findViewById(R.id.tvUse);
            tvExtra = itemView.findViewById(R.id.tvExtra);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
