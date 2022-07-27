package com.example.fmps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.SQLException;

class Dbconn extends SQLiteOpenHelper {
    private Context context;
    public static final String Database = "FMPS.db";

    public Dbconn(@Nullable Context context) {
        super(context, Database, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE erp_etudiants(id  INTEGER PRIMARY KEY AUTOINCREMENT,nom TEXT,douar Text , anne TEXT,section TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS erp_etudiants");
        onCreate(db);
    }

    public void insert(etudiant p,Context cn) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("nom", p.getNom());
            cv.put("douar", p.getDouar());
            cv.put("anne", p.getDouar());
            cv.put("section", p.getSection());
            db.insert("erp_etudiants", null, cv);
            Toast.makeText(cn, "Succes", Toast.LENGTH_SHORT).show();
            db.close();
        }catch (Exception x){
            Toast.makeText(cn, "Erreur", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateetudiant(etudiant p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("nom", p.getNom());
        cv.put("douar", p.getDouar());
        cv.put("anne", p.getDouar());
        cv.put("section", p.getSection());
        db.update("erp_etudiants", cv, "id=?", new String[]{String.valueOf(p.getId())});
        db.close();
    }

    public void deleteetudiant(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("erp_etudiants", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT*FROM erp_etudiants", null);
        return c;
    }
    public Cursor getEmployeeName(int empNo) {
        Cursor cursor = null;
        String empName = "";
        SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.query("erp_etudiants", new String[] { "id",
                            "nom", "douar" }, "id" + "=?",
                    new String[] { String.valueOf(empNo) }, null, null, null, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
            }
            return cursor;
    }
    public void rempliredown(Spinner txt , Context c) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(c,
                android.R.layout.simple_spinner_item, c.getResources()
                .getStringArray(R.array.country_array));//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txt.setAdapter(adapter);
    }
}