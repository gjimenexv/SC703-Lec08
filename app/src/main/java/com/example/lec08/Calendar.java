package com.example.lec08;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


public class Calendar extends Fragment {

    private EditText etTitulo, etUbicacion;
    private Button btFecha, btInicia, btTermina, btCrear;
    private TextView etFecha, etTermina, etInicia;
    private CheckBox ckEvento;

    public Calendar() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        etTitulo = root.findViewById(R.id.etTitulo);
        etUbicacion = root.findViewById(R.id.etUbicacion);
        btFecha = root.findViewById(R.id.btFecha);
        btInicia = root.findViewById(R.id.btInicia);
        btTermina = root.findViewById(R.id.btTermina);
        btCrear = root.findViewById(R.id.btCrear);
        etFecha = root.findViewById(R.id.etFecha);
        etInicia = root.findViewById(R.id.etInicia);
        etTermina = root.findViewById(R.id.etTermina);
        ckEvento = (CheckBox)root.findViewById(R.id.ckEvento);

        return root;
    }

    public void Calendario(View view) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int anno = cal.get(java.util.Calendar.YEAR);
        int mes = cal.get(java.util.Calendar.MONTH);
        int dia = cal.get(java.util.Calendar.DAY_OF_MONTH);

        DatePickerDialog date = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + month + "/" + year;
                etFecha.setText(fecha);
            }
        }, anno, mes, dia);

        date.show();
    }

    public void obtenerHora(View view) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int hora = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int minuto = cal.get(java.util.Calendar.MINUTE);

        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {
                etInicia.setText(hora + ":" + minuto);
            }
        }, hora, minuto, false);

        recogerHora.show();
    }

    public void terminaHora(View view) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int hora = cal.get(java.util.Calendar.HOUR_OF_DAY);
        int minuto = cal.get(java.util.Calendar.MINUTE);

        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hora, int minuto) {
                etTermina.setText(hora + ":" + minuto);
            }
        }, hora, minuto, false);

        recogerHora.show();
    }

    public void Agregar(View view) {
        java.util.Calendar cal =  java.util.Calendar.getInstance();

        cal.get(java.util.Calendar.YEAR);
        cal.get(java.util.Calendar.MONTH);
        cal.get(java.util.Calendar.DAY_OF_MONTH);

        cal.get(java.util.Calendar.HOUR_OF_DAY);
        cal.get(java.util.Calendar.MINUTE);

        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");

        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis()+60*60*1000);

        calIntent.putExtra(CalendarContract.Events.TITLE, etTitulo.getText().toString());
        calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, etUbicacion.getText().toString());
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);

        startActivity(calIntent);
    }
}