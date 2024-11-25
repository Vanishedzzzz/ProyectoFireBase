package com.example.proyectofirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText txtCodigo, txtNombre, txtDueño, txtDireccion;
    private ListView lista;
    private Spinner spMascota;

    private FirebaseFirestore db;

    String[] tipoMascotas = {"Perro", "Gato", "Pájaro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CargarListaFirestore();

        db = FirebaseFirestore.getInstance();

        // Inicializar los componentes de la interfaz
        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtDueño = findViewById(R.id.txtDueño);
        txtDireccion = findViewById(R.id.txtDireccion);
        lista = findViewById(R.id.lista);
        spMascota = findViewById(R.id.spMascota);

        // Configurar el Spinner con los tipos de mascotas
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipoMascotas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMascota.setAdapter(adapter);

    }

    public void CargarListaFirestore(){

    }

    // Método para enviar datos a Firebase Firestore
    // Metodo Enviar Datos:
    public void enviarDatosFirestore(View view) {
        // Obtenemos los campos ingresados en el formulario
        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String dueño = txtDueño.getText().toString();
        String direccion = txtDireccion.getText().toString();
        String tipoMascota = spMascota.getSelectedItem().toString();

        // Creamos un mapa con los datos a enviar
        Map<String, Object> mascota = new HashMap<>();
        mascota.put("codigo", codigo);
        mascota.put("nombre", nombre);
        mascota.put("dueño", dueño);
        mascota.put("direccion", direccion);
        mascota.put("tipoMascota", tipoMascota);

        // Enviamos los datos a firestore
        db.collection("mascotas")
                .document(codigo)
                .set(mascota)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(MainActivity.this, "Datos enviados a Firestore correctamente", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "Error al enviar datos a Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    public void cargarLista(View view){
        CargarListaFirestore();
    }


}
