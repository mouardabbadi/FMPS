package com.example.fmps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListeActivity extends AppCompatActivity {
    private TextView nom, classe;
    private Button chercher;
    private ListView content;
    private EditText search;
    int id;
    private HashMap<String, String> map;
    private ArrayList<HashMap<String, String>> Liste;
    Dbconn conn = new Dbconn(ListeActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        nom = (TextView) findViewById(R.id.nom);
        classe = (TextView) findViewById(R.id.classe);
        search = (EditText) findViewById(R.id.txtchercher);
        content = (ListView) findViewById(R.id.listeitem);
        Liste = new ArrayList<>();
        chercher = (Button) findViewById(R.id.chercherbtn);
        data();
        SimpleAdapter adapter = new SimpleAdapter(ListeActivity.this, Liste, R.layout.item, new String[]{"nom", "classe"}, new int[]{R.id.nom, R.id.classe});
        content.setAdapter(adapter);
        chercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search.getText().toString().isEmpty()) {
                    Liste.clear();
                    data();
                } else {
                    Cursor c = conn.getEmployeeName(Integer.parseInt(search.getText().toString()));
                    if (c.getCount() > 0) {
                        Liste.clear();
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("nom", c.getString(0));
                        map.put("classe", c.getString(2));
                        Liste.add(map);
                        Toast.makeText(ListeActivity.this, c.getString(2).toString(), Toast.LENGTH_SHORT).show();
                        SimpleAdapter adapter = new SimpleAdapter(ListeActivity.this, Liste, R.layout.item, new String[]{"nom", "classe"}, new int[]{R.id.nom, R.id.classe});
                        content.setAdapter(adapter);
                    }
                }
            }
        });
        content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = view.findViewById(R.id.nom);
                Toast.makeText(ListeActivity.this, t.getText(), Toast.LENGTH_SHORT).show();
                Dbconn conn = new Dbconn(ListeActivity.this);
                /*conn.deleteetudiant(Integer.parseInt(t.getText().toString()));
                Liste.clear();
                Toast.makeText(ListeActivity.this, "ss", Toast.LENGTH_SHORT).show();
                data();

                SimpleAdapter adapter = new SimpleAdapter(ListeActivity.this, Liste, R.layout.item, new String[]{ "nom", "classe"}, new int[]{R.id.nom, R.id.classe});
                content.setAdapter(adapter);*/
                final PopupMenu popup = new PopupMenu(ListeActivity.this,view);
                final MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()){
                            case  R.id.menusupprimer:
                                conn.deleteetudiant(Integer.parseInt(t.getText().toString()));
                                Toast.makeText(ListeActivity.this, "Supprimer Avec Succés", Toast.LENGTH_SHORT).show();
                                Liste.clear();
                                data();
                                return true;
                            case R.id.menuemodifier:
                                Intent i = new Intent(ListeActivity.this ,ConsultActivity.class);
                                startActivity(i);
                        }
                        return false;
                    }
                });
            }
        });

    }

    public void data() {
        Cursor c = conn.getAllProducts();
        c.moveToFirst();
        while (c.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("nom", c.getString(0));
            map.put("classe", c.getString(2));
            Liste.add(map);
        }
    }

    public void getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = getLayoutInflater();
        View row = inflater.inflate(R.layout.item, parent, false);
        ImageView deleteImageView = (ImageView) row.findViewById(R.id.imageView);
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });
        return ;
    }

}