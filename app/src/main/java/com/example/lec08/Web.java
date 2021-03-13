package com.example.lec08;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class Web extends Fragment {

    private EditText etURL;
    private ImageButton btNavigate;

    public Web() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_web, container, false);
        etURL = root.findViewById(R.id.etURL);
        btNavigate = root.findViewById(R.id.btNavigate);
        btNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navegar();
            }
        });
        return root;
    }

    private void navegar() {
        Uri webpage = Uri.parse("http://"+etURL.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}