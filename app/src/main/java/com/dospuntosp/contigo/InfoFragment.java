package com.dospuntosp.contigo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class InfoFragment extends Fragment {

    String _url7 = "https://www.instagram.com/end3cadencia/";
    private ImageButton btnImagenInsta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);
        btnImagenInsta = rootView.findViewById(R.id.imgButtonInsta);


        btnImagenInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri _link7 = Uri.parse(_url7);
                Log.d("click", "ok");
                Intent link7 = new Intent(Intent.ACTION_VIEW,_link7);
                startActivity(link7);
            }
        });
        // Inflate the layout for this fragment

        return rootView;
    }
}