package com.example.lec08;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


public class mail extends Fragment {

    private EditText et_to;
    private EditText et_subject;
    private EditText et_message;
    private ImageButton btEnviar;
    public mail() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mail, container, false);

        et_to = root.findViewById(R.id.et_to);
        et_subject = root.findViewById(R.id.et_subject);
        et_message = root.findViewById(R.id.et_message);
        btEnviar = root.findViewById(R.id.btEnviar);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });
        return root;
    }
    private void enviar() {
        String para=et_to.getText().toString();
        String asunto=et_subject.getText().toString();
        String mensaje=et_message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {para});
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        intent.putExtra(Intent.EXTRA_TEXT, mensaje);
        startActivity(intent);
    }
}