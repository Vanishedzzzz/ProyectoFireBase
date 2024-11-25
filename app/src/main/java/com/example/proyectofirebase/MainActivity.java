package com.example.proyectofirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

        // Cargar la lista de Firestore al iniciar
        CargarListaFirestore();
    }

    // Método para cargar los datos desde Firestore
    public void CargarListaFirestore() {
        // Obtenemos la instancia de Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Hacemos una consulta a la colección llamada "mascotas"
        db.collection("mascotas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> listaMascotas = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String linea = "ID: " + document.getId() + "\n" +
                                        "Nombre: " + document.getString("nombre") + "\n" +
                                        "Tipo: " + document.getString("tipoMascota") + "\n" +
                                        "Dueño: " + document.getString("dueño") + "\n" +
                                        "Dirección: " + document.getString("direccion");
                                listaMascotas.add(linea);
                            }
                            // Creamos un ArrayAdapter con la lista de elementos
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                                    android.R.layout.simple_list_item_1, listaMascotas);
                            lista.setAdapter(adapter);
                        } else {
                            // Si hay un error, se muestra el mensaje en el Logcat
                            Log.d("TAG", "Error al obtener datos de Firestore", task.getException());
                        }
                    }
                });
    }

    // Método para enviar datos a Firestore
    public void EnviarDatosFirestore(View view) {
        // Obtenemos los campos ingresados en el formulario
        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String dueño = txtDueño.getText().toString();
        String direccion = txtDireccion.getText().toString();
        String tipoMascota = spMascota.getSelectedItem().toString();

        // Validar que todos los campos están llenos
        if (codigo.isEmpty() || nombre.isEmpty() || dueño.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Creamos un mapa con los datos a enviar
        Map<String, Object> mascota = new HashMap<>();
        mascota.put("codigo", codigo);
        mascota.put("nombre", nombre);
        mascota.put("dueño", dueño);
        mascota.put("direccion", direccion);
        mascota.put("tipoMascota", tipoMascota);

        // Enviamos los datos a Firestore
        db.collection("mascotas")
                .document(codigo)  // Usamos el código como ID del documento
                .set(mascota)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(MainActivity.this, "Datos enviados a Firestore correctamente", Toast.LENGTH_SHORT).show();
                    // Después de enviar, recargamos la lista
                    CargarListaFirestore();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(MainActivity.this, "Error al enviar datos a Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    // Método para cargar la lista de Firestore, se puede llamar desde el botón
    public void CargarLista(View view) {
        CargarListaFirestore();
    }
}
