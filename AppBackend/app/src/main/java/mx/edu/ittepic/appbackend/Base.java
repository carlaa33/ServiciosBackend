package mx.edu.ittepic.appbackend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Base extends AppCompatActivity {
    EditText nombre,control;
    Button guardar,volver;
    ListView lv;

    List<String> alumnos;
    ArrayAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        nombre=findViewById(R.id.editNombre);
        control=findViewById(R.id.editControl);

        guardar=findViewById(R.id.btnGuardar);
        volver=findViewById(R.id.btnVolver);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Asistencia");

        lv=findViewById(R.id.LV);

        alumnos=new ArrayList<>();

        adapter= new ArrayAdapter<>(Base.this, android.R.layout.simple_list_item_1, alumnos);
        lv.setAdapter(adapter);


        database.getReference().child("Asistencia").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alumnos.removeAll(alumnos);
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Alumno alumno=snapshot.getValue(Alumno.class);
                    alumnos.add(alumno.getNoctrl()+" - "+alumno.getNombre());
                }
                adapter= new ArrayAdapter<>(Base.this, android.R.layout.simple_list_item_1, alumnos);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });



        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
            }
        });


    }

    private void volver() {
        finish();
    }

    private void guardar() {
        // Write a message to the database
        Alumno alumno=new Alumno(Integer.parseInt(control.getText().toString()),nombre.getText().toString());
        myRef.push().setValue(alumno);
    }
}
