package com.dospuntosp.contigo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioButton;

public class AddHorarioActivity extends AppCompatActivity {
    private EditText ed_nombreClase,ed_nombreProfesor,ed_sala, ed_horaIni, ed_horaTerm;
    private RadioGroup rg_Dias;
    private RadioButton rbLunes, rbMartes, rbMiercoles, rbJueves, rbViernes, rbSabado, rbDomingo;
    private Button b_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_horario);

        ed_nombreClase = findViewById(R.id.et_nombreClase);
        ed_nombreProfesor = findViewById(R.id.et_nombreProfesor);
        ed_sala = findViewById(R.id.et_sala);
        ed_horaIni = findViewById(R.id.et_horaIni);
        ed_horaTerm = findViewById(R.id.et_horaTerm);
        rg_Dias = findViewById(R.id.rgdias);
        rbLunes = findViewById(R.id.rbLunes);
        rbMartes = findViewById(R.id.rbMartes);
        rbMiercoles = findViewById(R.id.rbMiercoles);
        rbJueves = findViewById(R.id.rbJueves);
        rbViernes = findViewById(R.id.rbViernes);
        rbSabado = findViewById(R.id.rbSabado);
        rbDomingo = findViewById(R.id.rbDomingo);

        b_agregar = findViewById(R.id.btn_agregar);



        b_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });
    }
    public void insertar()
    {
        try
        {
            String nombreClase = ed_nombreClase.getText().toString();
            String nombreProfesor = ed_nombreProfesor.getText().toString();
            String sala = ed_sala.getText().toString();
            String horaIni = ed_horaIni.getText().toString();
            String horaTerm = ed_horaTerm.getText().toString();
            int idDeRBSelect = rg_Dias.getCheckedRadioButtonId();
            String dia = "";

            if (nombreClase.isEmpty()){
                ed_nombreClase.setError("Ingrese un nombre de clase");
            }if (nombreProfesor.isEmpty()){
                ed_nombreProfesor.setError("Ingrese el nombre del Profesor");
            }if (sala.isEmpty()){
                ed_sala.setError("Ingrese la sala");
            }if (horaIni.isEmpty()){
                ed_horaIni.setError("Ingrese la hora de inicio");
            }if (horaTerm.isEmpty()){
                ed_horaTerm.setError("Ingrese la hora de termino");
            }else {

                if (idDeRBSelect == rbLunes.getId()) {
                    dia = "Lunes";
                } else if (idDeRBSelect == rbMartes.getId()) {
                    dia = "Martes";
                } else if (idDeRBSelect == rbMiercoles.getId()) {
                    dia = "Miercoles";
                } else if (idDeRBSelect == rbJueves.getId()) {
                    dia = "Jueves";
                } else if (idDeRBSelect == rbViernes.getId()) {
                    dia = "Viernes";
                } else if (idDeRBSelect == rbSabado.getId()) {
                    dia = "Sabado";
                } else if (idDeRBSelect == rbDomingo.getId()) {
                    dia = "Domingo";
                } else {
                    Toast.makeText(this, "No se a seleccionado dia.", Toast.LENGTH_LONG).show();
                }

                SQLiteDatabase db = openOrCreateDatabase("DB_CONTIGO", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS horario(id INTEGER PRIMARY KEY AUTOINCREMENT,nombreClase VARCHAR,nombreProfesor VARCHAR,sala VARCHAR, horaIni VARCHAR, horaTerm VARCHAR , dia varchar)");

                String sql = "insert into horario(nombreClase, nombreProfesor, sala, horaIni , horaTerm, dia)values(?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, nombreClase);
                statement.bindString(2, nombreProfesor);
                statement.bindString(3, sala);
                statement.bindString(4, horaIni);
                statement.bindString(5, horaTerm);
                statement.bindString(6, dia);
                statement.execute();
                Toast.makeText(this, "Datos agregados satisfactoriamente en la base de datos.", Toast.LENGTH_LONG).show();

                ed_nombreClase.setText("");
                ed_nombreProfesor.setText("");
                ed_sala.setText("");
                ed_horaIni.setText("");
                ed_horaTerm.setText("");
                ed_nombreClase.requestFocus();
        }
        }
        catch (Exception ex)
        {
            Toast.makeText(this,"Error no se pudieron guardar los datos.",Toast.LENGTH_LONG).show();
        }
    }
}