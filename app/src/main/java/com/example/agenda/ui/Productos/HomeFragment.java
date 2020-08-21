package com.example.agenda.ui.Productos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.agenda.AdminSQLiteOpenHelper;
import com.example.agenda.R;
import com.example.agenda.vars;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private EditText nombre,precio;
    private  Button agregar;
    private ContentValues registro;
    private ListView lv1;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_producto, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        nombre = (EditText)root.findViewById(R.id.nombre);
        precio = (EditText)root.findViewById(R.id.precio);
        agregar=(Button) root.findViewById(R.id.agregarProducto);

        lv1 = (ListView)root.findViewById(R.id.listViewPersonas);




        ArrayList <String> ranking = new ArrayList<>();

        AdminSQLiteOpenHelper producto = new AdminSQLiteOpenHelper(getContext(), "producto", null, 7);
        SQLiteDatabase bd = producto.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from alarma", null);
        if(fila.moveToFirst()){
            do{
                ranking.add(fila.getString(0) + " - " + fila.getString(1));
            }while(fila.moveToNext());
        }
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, ranking);
        lv1.setAdapter(adapter);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenar111();
            }


        });







        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });






        return root;
    }



    public void llenar111() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getContext(), vars.bd, null, vars.version);
        SQLiteDatabase bd = admin.getReadableDatabase();
        bd = admin.getWritableDatabase();
        registro = new ContentValues();
        registro.put("nombre", nombre.getText().toString());
        registro.put("precio", precio.getText().toString());//nombre del campo
        bd.insert("producto", null, registro);//nombre de la tabla
        bd.close();
        nombre.setText("");
        precio.setText("");

        Toast.makeText(getContext(), "producto registrada", Toast.LENGTH_LONG).show();

    }



}