package mx.edu.ittepic.appbackend;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Inicio extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    TextView email;
    EditText nombre;

    Button salir,actualizar,asistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        mAuth = FirebaseAuth.getInstance();

        user = mAuth.getCurrentUser();

        nombre=findViewById(R.id.editNombre);
        email=findViewById(R.id.textView5);
        nombre.setText(user.getDisplayName());
        email.setText(user.getEmail());

        salir=findViewById(R.id.btnLogout);
        actualizar=findViewById(R.id.btnGuardar);
        asistencia=findViewById(R.id.btnAsistencia);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                finish();
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos(nombre.getText().toString());
            }
        });
        asistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ventana=new Intent(Inicio.this,Base.class);
                startActivity(ventana);
            }
        });
    }

    private void guardarDatos(String nombre) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nombre)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Inicio.this, "Se guardó el nombre.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
