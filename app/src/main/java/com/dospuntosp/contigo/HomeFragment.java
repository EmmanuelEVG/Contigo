package com.dospuntosp.contigo;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {
    private TextView nombreTexto;
    private RecyclerView recyclerViewTarea, recyclerViewPrueba, recyclerViewMedico, recyclerViewOtro;

    private TareasAdapter tareasAdapterTarea, tareasAdapterPrueba, tareasAdapterMedico, tareasAdapterOtro;
    private ArrayList<Tarea> listaTareasTarea, listaTareasPrueba, listaTareasMedico, listaTareasOtro;

    private HorarioAdapter horarioAdapterLunes, horarioAdapterMartes, horarioAdapterMiercoles, horarioAdapterJueves, horarioAdapterViernes, horarioAdapterSabado, horarioAdapterDomingo;

    private ArrayList<Horario> listaHorarioLunes, listaHorarioMartes, listaHorarioMiercoles, listaHorarioJueves, listaHorarioViernes, listaHorarioSabado, listaHorarioDomingo;
    private RecyclerView recyclerViewLunes, recyclerViewMartes, recyclerViewMiercoles, recyclerViewJueves, recyclerViewViernes,recyclerViewSabado, recyclerViewDomingo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Iniciando el TextView
        nombreTexto = rootView.findViewById(R.id.textviewNombre);

        // Obteniendo el nombre del usuario
        String nombreUsuario = obtenerUltimoUsuarioSqlite();
        nombreTexto.setText("Bienvenido " + nombreUsuario);

        // Obteniendo datos de la base de datos
        List<Tarea> listaTareas = obtenerDatosDeBaseDeDatos();
        List<Horario> listaHorario = obtenerDatosPorTipoDeDia("");

        // Mostrando información de depuración
        Log.d("TareasAdapter", "Cantidad de tareas obtenidas: " + listaTareas.size());

        if (listaHorario != null){

            recyclerViewLunes = rootView.findViewById(R.id.recyclerViewLunes);
            recyclerViewLunes.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
            listaHorarioLunes = new ArrayList<>(obtenerDatosPorTipoDeDia("Lunes"));
            horarioAdapterLunes = new HorarioAdapter(requireContext(), listaHorarioLunes);
            recyclerViewLunes.setAdapter(horarioAdapterLunes);

            recyclerViewMartes = rootView.findViewById(R.id.recyclerViewMartes);
            recyclerViewMartes.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
            listaHorarioMartes = new ArrayList<>(obtenerDatosPorTipoDeDia("Martes"));
            horarioAdapterMartes = new HorarioAdapter(requireContext(), listaHorarioMartes);
            recyclerViewMartes.setAdapter(horarioAdapterMartes);

            recyclerViewMiercoles = rootView.findViewById(R.id.recyclerViewMiercoles);
            recyclerViewMiercoles.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
            listaHorarioMiercoles = new ArrayList<>(obtenerDatosPorTipoDeDia("Miercoles"));
            horarioAdapterMiercoles = new HorarioAdapter(requireContext(), listaHorarioMiercoles);
            recyclerViewMiercoles.setAdapter(horarioAdapterMiercoles);

            recyclerViewJueves = rootView.findViewById(R.id.recyclerViewJueves);
            recyclerViewJueves.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
            listaHorarioJueves = new ArrayList<>(obtenerDatosPorTipoDeDia("Jueves"));
            horarioAdapterJueves= new HorarioAdapter(requireContext(), listaHorarioJueves);
            recyclerViewJueves.setAdapter(horarioAdapterJueves);

            recyclerViewViernes = rootView.findViewById(R.id.recyclerViewViernes);
            recyclerViewViernes.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
            listaHorarioViernes = new ArrayList<>(obtenerDatosPorTipoDeDia("Viernes"));
            horarioAdapterViernes = new HorarioAdapter(requireContext(), listaHorarioViernes);
            recyclerViewViernes.setAdapter(horarioAdapterViernes);

            recyclerViewSabado = rootView.findViewById(R.id.recyclerViewSabado);
            recyclerViewSabado.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
            listaHorarioSabado = new ArrayList<>(obtenerDatosPorTipoDeDia("Sabado"));
            horarioAdapterSabado = new HorarioAdapter(requireContext(), listaHorarioSabado);
            recyclerViewSabado.setAdapter(horarioAdapterSabado);

            recyclerViewDomingo = rootView.findViewById(R.id.recyclerViewDomingo);
            recyclerViewDomingo.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
            listaHorarioDomingo = new ArrayList<>(obtenerDatosPorTipoDeDia("Domingo"));
            horarioAdapterDomingo = new HorarioAdapter(requireContext(), listaHorarioDomingo);
            recyclerViewDomingo.setAdapter(horarioAdapterDomingo);
        }else {
            Log.d("TareasAdapter", "La lista de horario está vacía o nula");
        }

        // Inicializando RecyclerView y adaptador solo si hay datos
        if (listaTareas != null && !listaTareas.isEmpty()) {
            // Inicializando RecyclerView
            recyclerViewTarea = rootView.findViewById(R.id.recyclerViewTareas);
            recyclerViewTarea.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            listaTareasTarea = new ArrayList<>(obtenerDatosPorTipoDePendiente("Tarea"));
            tareasAdapterTarea = new TareasAdapter(requireContext(), listaTareasTarea);
            recyclerViewTarea.setAdapter(tareasAdapterTarea);

            recyclerViewPrueba = rootView.findViewById(R.id.recyclerViewPruebas);
            recyclerViewPrueba.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            listaTareasPrueba = new ArrayList<>(obtenerDatosPorTipoDePendiente("Prueba"));
            tareasAdapterPrueba = new TareasAdapter(requireContext(), listaTareasPrueba);
            recyclerViewPrueba.setAdapter(tareasAdapterPrueba);

            recyclerViewMedico = rootView.findViewById(R.id.recyclerViewMedico);
            recyclerViewMedico.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            listaTareasMedico = new ArrayList<>(obtenerDatosPorTipoDePendiente("Medico"));
            tareasAdapterMedico = new TareasAdapter(requireContext(), listaTareasMedico);
            recyclerViewMedico.setAdapter(tareasAdapterMedico);

            recyclerViewOtro = rootView.findViewById(R.id.recyclerViewOtros);
            recyclerViewOtro.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            listaTareasOtro = new ArrayList<>(obtenerDatosPorTipoDePendiente("Otro"));
            tareasAdapterOtro = new TareasAdapter(requireContext(), listaTareasOtro);
            recyclerViewOtro.setAdapter(tareasAdapterOtro);

        } else {
            // Mostrando información de depuración si la lista está vacía o nula
            Log.d("TareasAdapter", "La lista de tareas está vacía o nula");
        }


        return rootView;
    }


    private List<Horario> obtenerDatosPorTipoDeDia(String tipoDia) {
        List<Horario> listaHorario = new ArrayList<>();

        try {
            SQLiteDatabase db2 = requireContext().openOrCreateDatabase("DB_CONTIGO", Context.MODE_PRIVATE, null);
            String query2 = "SELECT * FROM horario WHERE dia=?";
            Cursor cursor = db2.rawQuery(query2, new String[]{tipoDia});

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Horario horario = new Horario();
                    horario.setNombreClase(cursor.getString(cursor.getColumnIndex("nombreClase")));
                    horario.setNombreProfesor(cursor.getString(cursor.getColumnIndex("nombreProfesor")));
                    horario.setHoraIni(cursor.getString(cursor.getColumnIndex("horaIni")));
                    horario.setHoraTerm(cursor.getString(cursor.getColumnIndex("horaTerm")));
                    horario.setSala(cursor.getString(cursor.getColumnIndex("sala")));
                    horario.setDia(cursor.getString(cursor.getColumnIndex("dia")));

                    listaHorario.add(horario);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db2.close();

            Collections.sort(listaHorario, new Comparator<Horario>() {
                @Override
                public int compare(Horario horario1, Horario horario2) {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    try {
                        Date hora1 = sdf.parse(horario1.getHoraIni());
                        Date hora2 = sdf.parse(horario2.getHoraIni());
                        return hora1.compareTo(hora2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaHorario;
    }

    private List<Tarea> obtenerDatosPorTipoDePendiente(String tipoPendiente) {
        List<Tarea> listaTareas = new ArrayList<>();

        try {
            SQLiteDatabase db = requireContext().openOrCreateDatabase("DB_CONTIGO", Context.MODE_PRIVATE, null);
            String query = "SELECT * FROM pendientes WHERE pendiente=?";
            Cursor cursor = db.rawQuery(query, new String[]{tipoPendiente});

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Tarea tarea = new Tarea();
                    tarea.setNombreClase(cursor.getString(cursor.getColumnIndex("nombreClase")));
                    tarea.setHoraEntrega(cursor.getString(cursor.getColumnIndex("horaEntrega")));
                    tarea.setDiaEntrega(cursor.getString(cursor.getColumnIndex("diaEntrega")));
                    tarea.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
                    tarea.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                    tarea.setPrioridad(cursor.getString(cursor.getColumnIndex("prioridad")));
                    tarea.setPendiente(cursor.getString(cursor.getColumnIndex("pendiente")));

                    listaTareas.add(tarea);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaTareas;
    }


    public List<Tarea> obtenerDatosDeBaseDeDatos() {
        List<Tarea> listaTareas = new ArrayList<>();

        try {
            SQLiteDatabase db = requireContext().openOrCreateDatabase("DB_CONTIGO", Context.MODE_PRIVATE, null);
            Cursor cursor = db.rawQuery("SELECT * FROM pendientes", null);


            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Tarea tarea = new Tarea();
                    tarea.setNombreClase(cursor.getString(cursor.getColumnIndex("nombreClase")));
                    tarea.setHoraEntrega(cursor.getString(cursor.getColumnIndex("horaEntrega")));
                    tarea.setDiaEntrega(cursor.getString(cursor.getColumnIndex("diaEntrega")));
                    tarea.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
                    tarea.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                    tarea.setPrioridad(cursor.getString(cursor.getColumnIndex("prioridad")));
                    tarea.setPendiente(cursor.getString(cursor.getColumnIndex("pendiente")));

                    listaTareas.add(tarea);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaTareas;
    }


    private String obtenerUltimoUsuarioSqlite() {
        String nombreUsuario = "";
        try {
            SQLiteDatabase db = getActivity().openOrCreateDatabase("DB_CONTIGO", Context.MODE_PRIVATE, null);

            // Obtener el último usuario
            Cursor cursor = db.rawQuery("SELECT user FROM usuario ORDER BY id DESC LIMIT 1", null);
            if (cursor.moveToFirst()) {
                nombreUsuario = cursor.getString(cursor.getColumnIndex("user"));
            }
            cursor.close();

            // Borrar usuarios antiguos (opcional)
            db.execSQL("DELETE FROM usuario WHERE id < (SELECT MAX(id) FROM usuario)");

            db.close();
        } catch (SQLiteException e) {
            // Manejar errores, como la falta de la tabla o la base de datos
            e.printStackTrace();
        }

        return nombreUsuario;
    }
}
