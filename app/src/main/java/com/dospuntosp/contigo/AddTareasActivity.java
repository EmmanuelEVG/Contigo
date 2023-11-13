package com.dospuntosp.contigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddTareasActivity extends AppCompatActivity {
    private EditText ed_nombreClase, ed_horaEntrega, ed_diaEntrega, ed_titulo, ed_descripcion;
    private RadioGroup rg_Prioridad, rg_TipoPendiente;
    private RadioButton rb_Baja, rb_Media, rb_Alta, rb_Tarea, rb_Prueba, rb_Medico, rb_Otro;
    private Button b_agregarPendiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tareas);

        ed_nombreClase = findViewById(R.id.et_nombreClase);
        ed_horaEntrega = findViewById(R.id.et_horaEntrega);
        ed_diaEntrega = findViewById(R.id.et_diaEntrega);
        ed_titulo = findViewById(R.id.et_titulo);
        ed_descripcion = findViewById(R.id.et_descripcion);

        rg_Prioridad = findViewById(R.id.rgprioridad);
        rg_TipoPendiente = findViewById(R.id.rgTipoPendiente);

        rb_Baja = findViewById(R.id.rbPBaja);
        rb_Media = findViewById(R.id.rbPMedia);
        rb_Alta = findViewById(R.id.rbPAlta);

        rb_Tarea = findViewById(R.id.rbTarea);
        rb_Prueba = findViewById(R.id.rbPrueba);
        rb_Medico = findViewById(R.id.rbMedico);
        rb_Otro = findViewById(R.id.rbOtro);

        b_agregarPendiente = findViewById(R.id.btn_agregarPendiente);

        b_agregarPendiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarTarea();
            }
        });
    }

    public void insertarTarea() {
        try {
            String nombreClase = ed_nombreClase.getText().toString();
            String horaEntrega = ed_horaEntrega.getText().toString();
            String diaEntrega = ed_diaEntrega.getText().toString();
            String titulo = ed_titulo.getText().toString();
            String descripcion = ed_descripcion.getText().toString();
            String prioridad = "";
            String pendiente = "";
            int idDeRBSelect = rg_Prioridad.getCheckedRadioButtonId();
            int idDePendiente = rg_TipoPendiente.getCheckedRadioButtonId();

            if (nombreClase.isEmpty()) {
                ed_nombreClase.setError("Ingrese un nombre de clase");
            }
            if (horaEntrega.isEmpty()) {
                ed_horaEntrega.setError("Ingrese la Hora de entrega");
            }
            if (diaEntrega.isEmpty()) {
                ed_diaEntrega.setError("Ingrese el dia de entrega");
            }
            if (titulo.isEmpty()) {
                ed_titulo.setError("Ingrese un titulo o lo que es");
            }
            if (descripcion.isEmpty()) {
                ed_descripcion.setError("Ingrese una descripcion breve o larga");
            } else {
                if (idDeRBSelect == rb_Baja.getId()) {
                    prioridad = "Baja";
                } else if (idDeRBSelect == rb_Media.getId()) {
                    prioridad = "Media";
                } else if (idDeRBSelect == rb_Alta.getId()) {
                    prioridad = "Alta";
                } else {
                    Toast.makeText(this, "No se a seleccionado Prioridad.", Toast.LENGTH_LONG).show();
                }

                if (idDePendiente == rb_Tarea.getId()) {
                    pendiente = "Tarea";
                } else if (idDePendiente == rb_Prueba.getId()) {
                    pendiente = "Prueba";
                } else if (idDePendiente == rb_Medico.getId()) {
                    pendiente = "Medico";
                } else if (idDePendiente == rb_Otro.getId()) {
                    pendiente = "Otro";
                } else {
                    Toast.makeText(this, "No se a seleccionado Tipo de Pendiente.", Toast.LENGTH_LONG).show();
                }

                SQLiteDatabase db = openOrCreateDatabase("DB_CONTIGO", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS pendientes(id INTEGER PRIMARY KEY AUTOINCREMENT,nombreClase VARCHAR,horaEntrega VARCHAR,diaEntrega VARCHAR, titulo VARCHAR, descripcion VARCHAR , prioridad VARCHAR, pendiente VARCHAR)");

                String sql = "insert into pendientes(nombreClase, horaEntrega, diaEntrega, titulo , descripcion, prioridad, pendiente)values(?,?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, nombreClase);
                statement.bindString(2, horaEntrega);
                statement.bindString(3, diaEntrega);
                statement.bindString(4, titulo);
                statement.bindString(5, descripcion);
                statement.bindString(6, prioridad);
                statement.bindString(7, pendiente);
                statement.execute();
                Toast.makeText(this, "Datos agregados satisfactoriamente en la base de datos.", Toast.LENGTH_LONG).show();

                ed_nombreClase.setText("");
                ed_horaEntrega.setText("");
                ed_diaEntrega.setText("");
                ed_titulo.setText("");
                ed_descripcion.setText("");
                ed_nombreClase.requestFocus();
            }

        } catch (Exception ex) {
            Toast.makeText(this, "Error no se pudieron guardar los datos.", Toast.LENGTH_LONG).show();
        }
    }
}