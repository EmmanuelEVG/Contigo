package com.dospuntosp.contigo;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {
    private List<Tarea> listaTareas;

    public TareasAdapter(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    private Context context;

    public TareasAdapter(Context context, List<Tarea> listaTareas) {
        this.context = context;
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = listaTareas.get(position);

        // Configura las vistas del ViewHolder con los datos de la Tarea

        holder.tv_NombreClase.setText(tarea.getNombreClase());
        holder.tv_Titulo.setText(tarea.getTitulo());
        holder.tv_HoraEntrega.setText(tarea.getHoraEntrega());
        holder.tv_DiaEntrega.setText(tarea.getDiaEntrega());
        holder.tv_Prioridad.setText(tarea.getPrioridad());
        // ... otros campos ...
        String prioridad = tarea.getPrioridad();
        int colorPrioridad = obtenerColorPorPrioridad(prioridad);
        holder.tv_Prioridad.setBackgroundColor(colorPrioridad);
    }
    private int obtenerColorPorPrioridad(String prioridad) {
        int colorResId;

        switch (prioridad) {
            case "Baja":
                colorResId = R.color.colorAlta;
                break;
            case "Media":
                colorResId = R.color.colorMedia;
                break;
            case "Alta":
                colorResId = R.color.colorBaja;
                break;
            default:
                colorResId = android.R.color.black; // Color predeterminado en caso de que no coincida con ninguna prioridad
                break;
        }

        return ContextCompat.getColor(context, colorResId);
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    static class TareaViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_NombreClase, tv_Titulo, tv_HoraEntrega, tv_DiaEntrega, tv_Prioridad;
        // ... otros campos ...

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_NombreClase = itemView.findViewById(R.id.tvNombreClase);
            tv_Titulo = itemView.findViewById(R.id.tvTitulo);
            tv_HoraEntrega = itemView.findViewById(R.id.tvHoraEntrega);
            tv_DiaEntrega = itemView.findViewById(R.id.tvDiaEntrega);
            tv_Prioridad = itemView.findViewById(R.id.tvPrioridad);
        }
    }
}
