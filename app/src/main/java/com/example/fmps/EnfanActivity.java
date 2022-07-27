package com.example.fmps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.Calendar;

public class EnfanActivity extends AppCompatActivity {
    Spinner spinCountry;
    private Button ajouter;
    private EditText nom,datetime;
    private Spinner douar;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enfan);
        Dbconn conn = new Dbconn(EnfanActivity.this);
        spinCountry= (Spinner) findViewById(R.id.down);//fetch the spinner from layout file
        conn.rempliredown(spinCountry,EnfanActivity.this);

        nom = (EditText) findViewById(R.id.nomtxt);
        douar = (Spinner) findViewById(R.id.down) ;
        datetime = (EditText) findViewById(R.id.datetime);
        Calendar calendar =Calendar.getInstance();
        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        EnfanActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String data = dayOfMonth+"/"+month+"/"+year;
                datetime.setText(data.toString());
            }
        };
    }
}