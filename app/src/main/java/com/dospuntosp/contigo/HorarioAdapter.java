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

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>{

    private List<Horario> listaHorario;
    public HorarioAdapter(List<Horario> listaHorario){
        this.listaHorario = listaHorario;
    }

    private Context context;

    public HorarioAdapter(Context context, List<Horario> listaHorario){
        this.context = context;
        this.listaHorario = listaHorario;
    }

    static class HorarioViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_NombreClase, tv_Sala, tv_HoraIni, tv_HoraTerm, tv_NombreProfesor;
        // ... otros campos ...

        public HorarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_NombreClase = itemView.findViewById(R.id.tvNombreClase);
            tv_Sala = itemView.findViewById(R.id.tvSala);
            tv_HoraIni = itemView.findViewById(R.id.tvHoraIni);
            tv_HoraTerm = itemView.findViewById(R.id.tvHoraTerm);
            tv_NombreProfesor = itemView.findViewById(R.id.tvnombreProfesor);
        }
    }
    @NonNull
    @Override
    public HorarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.item_horario, parent,false);
        return new HorarioViewHolder(view);

    }

    @Override
    public int getItemCount() {
        return listaHorario.size();
    }

    public void onBindViewHolder(@NonNull HorarioViewHolder holder, int position){
        Horario horario = listaHorario.get(position);

        holder.tv_NombreClase.setText(horario.getNombreClase());
        holder.tv_Sala.setText(horario.getSala());
        holder.tv_HoraIni.setText(horario.getHoraIni());
        holder.tv_HoraTerm.setText(horario.getHoraTerm());
        holder.tv_NombreProfesor.setText(horario.getNombreProfesor());
    }
}
