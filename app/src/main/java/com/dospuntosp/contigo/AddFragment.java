package com.dospuntosp.contigo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AddFragment extends Fragment {

    private Button btnaddHorario, btnaddRecordatorio;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        // Inflate the layout for this fragment

        btnaddHorario = rootView.findViewById(R.id.add_Horarios);
        btnaddRecordatorio = rootView.findViewById(R.id.add_Recordatorios);

        btnaddHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AddHorarioActivity.class);
                startActivity(intent);
            }
        });

        btnaddRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AddTareasActivity.class);
                startActivity(intent);
            }
        });

        return rootView;

    }

}