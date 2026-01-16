package com.example.navigation_drawer_app;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "staor.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_ITEMS = "items";

    public DataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + TABLE_ITEMS + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tipo TEXT NOT NULL," +

                        // comunes
                        "brand TEXT NOT NULL," +
                        "use_text TEXT," +
                        "color TEXT," +

                        // pen
                        "ink_color TEXT," +
                        "type_pen TEXT," +

                        // notebook
                        "no_sheets INTEGER," +

                        // ink
                        "quantity INTEGER," +

                        // pencil
                        "type_graph TEXT," +

                        "created_at INTEGER DEFAULT (strftime('%s','now'))" +
                        ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public long insertItem(
            String tipo,
            String brand,
            String useText,
            String color,
            @Nullable String inkColor,
            @Nullable String typePen,
            @Nullable Integer noSheets,
            @Nullable Integer quantity,
            @Nullable String typeGraph
    ) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("tipo", tipo);
        cv.put("brand", brand);
        cv.put("use_text", useText);
        cv.put("color", color);

        if (inkColor != null) cv.put("ink_color", inkColor);
        if (typePen != null) cv.put("type_pen", typePen);
        if (noSheets != null) cv.put("no_sheets", noSheets);
        if (quantity != null) cv.put("quantity", quantity);
        if (typeGraph != null) cv.put("type_graph", typeGraph);

        return db.insert(TABLE_ITEMS, null, cv);
    }
    public List<Item> getItemsByTipo(String tipo) {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(
                TABLE_ITEMS,
                null,
                "tipo = ?",
                new String[]{tipo},
                null, null,
                "id DESC"
        );

        while (c.moveToNext()) {
            Item it = new Item();
            it.id = c.getLong(c.getColumnIndexOrThrow("id"));
            it.tipo = c.getString(c.getColumnIndexOrThrow("tipo"));
            it.brand = c.getString(c.getColumnIndexOrThrow("brand"));
            it.useText = c.getString(c.getColumnIndexOrThrow("use_text"));
            it.color = c.getString(c.getColumnIndexOrThrow("color"));
            it.inkColor = c.getString(c.getColumnIndexOrThrow("ink_color"));
            it.typePen = c.getString(c.getColumnIndexOrThrow("type_pen"));
            it.noSheets = c.isNull(c.getColumnIndexOrThrow("no_sheets")) ? null : c.getInt(c.getColumnIndexOrThrow("no_sheets"));
            it.quantity = c.isNull(c.getColumnIndexOrThrow("quantity")) ? null : c.getInt(c.getColumnIndexOrThrow("quantity"));
            it.typeGraph = c.getString(c.getColumnIndexOrThrow("type_graph"));
            list.add(it);
        }

        c.close();
        return list;
    }

    public int deleteItem(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_ITEMS, "id = ?", new String[]{String.valueOf(id)});
    }

    public int updateItem(
            long id,
            String tipo,
            String brand,
            String useText,
            String color,
            @Nullable String inkColor,
            @Nullable String typePen,
            @Nullable Integer noSheets,
            @Nullable Integer quantity,
            @Nullable String typeGraph
    ) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("tipo", tipo);
        cv.put("brand", brand);
        cv.put("use_text", useText);
        cv.put("color", color);
        cv.put("ink_color", inkColor);
        cv.put("type_pen", typePen);
        cv.put("no_sheets", noSheets);
        cv.put("quantity", quantity);
        cv.put("type_graph", typeGraph);

        return db.update(TABLE_ITEMS, cv, "id = ?", new String[]{String.valueOf(id)});
    }
    public Item getItemById(long id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(
                TABLE_ITEMS,
                null,
                "id = ?",
                new String[]{String.valueOf(id)},
                null, null, null
        );

        try {
            if (!c.moveToFirst()) return null;

            Item it = new Item();
            it.id = c.getLong(c.getColumnIndexOrThrow("id"));
            it.tipo = c.getString(c.getColumnIndexOrThrow("tipo"));
            it.brand = c.getString(c.getColumnIndexOrThrow("brand"));
            it.useText = c.getString(c.getColumnIndexOrThrow("use_text"));
            it.color = c.getString(c.getColumnIndexOrThrow("color"));

            it.inkColor = c.getString(c.getColumnIndexOrThrow("ink_color"));
            it.typePen = c.getString(c.getColumnIndexOrThrow("type_pen"));

            it.noSheets = c.isNull(c.getColumnIndexOrThrow("no_sheets"))
                    ? null : c.getInt(c.getColumnIndexOrThrow("no_sheets"));

            it.quantity = c.isNull(c.getColumnIndexOrThrow("quantity"))
                    ? null : c.getInt(c.getColumnIndexOrThrow("quantity"));

            it.typeGraph = c.getString(c.getColumnIndexOrThrow("type_graph"));

            return it;
        } finally {
            c.close();
        }
    }


}