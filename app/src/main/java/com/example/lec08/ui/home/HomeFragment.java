package com.example.lec08.ui.home;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.lec08.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    private Switch circular;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button bt_gato = root.findViewById(R.id.bt_gato);
        Button bt_leon = root.findViewById(R.id.bt_leon);
        Button bt_play = root.findViewById(R.id.bt_play);
        Button bt_pause = root.findViewById(R.id.bt_pause);
        Button bt_cont = root.findViewById(R.id.bt_cont);
        Button bt_stop = root.findViewById(R.id.bt_stop);
        circular = root.findViewById(R.id.bt_ciclo);

        bt_gato.setOnClickListener(this);
        bt_leon.setOnClickListener(this);
        bt_play.setOnClickListener(this);
        bt_pause.setOnClickListener(this);
        bt_cont.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
        circular.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_gato:
                gato();
                break;
            case R.id.bt_leon:
                leon();
                break;
            case R.id.bt_play:
                play();
                break;
            case R.id.bt_pause:
                pausar();
                break;
            case R.id.bt_cont:
                continuar();
                break;
            case R.id.bt_stop:
                detener();
                break;                
        }
    }

    private void gato() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),R.raw.gato);
        mediaPlayer.start();
    }

    private void leon() {
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),R.raw.leon);
        mediaPlayer.start();
    }

    private MediaPlayer musica;
    private int posicion;

    private void destruir(){
        if (musica!=null){
            musica.release();
        }
    }

    private void play() {
        destruir();
        musica = MediaPlayer.create(getContext(), R.raw.musica);
        musica.start();
        musica.setLooping(circular.isChecked());
    }

    private void pausar() {
        if (musica!= null && musica.isPlaying()){
            posicion = musica.getCurrentPosition();
            musica.pause();
        }
    }

    private void continuar() {
        if (musica!= null && musica.isPlaying() == false){
            musica.seekTo(posicion);
            musica.start();
        }
    }

    private void detener() {
        if (musica!=null){
            musica.stop();
            posicion=0;
        }
    }
}