package com.example.lec08;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Phone extends Fragment implements View.OnClickListener  {

    private TextView etSms;
    private TextView etTelefono;
    private Button bt_llamar;
    private Button bt_llamar_directo;
    private Button bt_sms;
    private Button bt_sms_directo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        etTelefono = root.findViewById(R.id.etTelefono);
        etSms = root.findViewById(R.id.etSms);

        bt_llamar = root.findViewById(R.id.bt_llamar);
        bt_llamar_directo = root.findViewById(R.id.bt_llamar_directo);
        bt_sms = root.findViewById(R.id.bt_sms);
        bt_sms_directo = root.findViewById(R.id.bt_sms_directo);


        bt_llamar.setOnClickListener(this);
        bt_llamar_directo.setOnClickListener(this);
        bt_sms.setOnClickListener(this);
        bt_sms_directo.setOnClickListener(this);


        return root;


    }//fin de oncreate

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_llamar:
                llamar();
                break;
            case R.id.bt_llamar_directo:
                llamarDirecto();
                break;
            case R.id.bt_sms:
                mensaje();
                break;
            case R.id.bt_sms_directo:
                mensajeDirecto();
                break;


        }

    }//fin de onclick


    private void llamarDirecto() {

        String telefono = etTelefono.getText().toString();
        Uri uri = Uri.parse("tel:" + telefono);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(

                getContext(),

                Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.CALL_PHONE}, 105);
        } else {
            startActivity(intent);
        }


    }

    //  === código elemental para hacer que aparezca la ventana de marcar a un teléfono ====
    private void llamar() {

        String telefono = etTelefono.getText().toString();
        Uri uri = Uri.parse("tel:" + telefono);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);

    }//fin de llamar


    private void mensaje() {

        //=== código para enviar un mensaje de texto tipo sms ===

        String sms = etSms.getText().toString();
        Uri uri;
        uri = Uri.parse("smsto:" + sms);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);

        String texto;
        texto = etSms.getText().toString();
        intent.putExtra("sms_body", texto);
        startActivity(intent);

    }

    private void mensajeDirecto() {

        //=== código para enviar un mensaje de texto tipo SMS directo ===  se debe validar si se requiere algo adicional en el manifest.xml

        String sms = etSms.getText().toString();
        String texto = etSms.getText().toString();
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(sms, null, texto, null, null);
        Toast.makeText(getContext(), "SMS enviado.", Toast.LENGTH_LONG).show();

    }
}