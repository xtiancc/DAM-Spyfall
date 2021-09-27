package com.example.spyfall

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*
import java.security.AccessController.getContext
import java.util.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Se obtienen los segundos insertados por el usuario en el activity Configure
        var preferences: SharedPreferences = getSharedPreferences("configure", Context.MODE_PRIVATE);
        var time = preferences.getString("time", "")?.toLong();

        // Mediaplayer necesario para el final de la cuenta atrás
        var mp = MediaPlayer.create(this, R.raw.countdown);

        if (time != null) {

            // Pasamos los minutos a milisegundos
            time *= 60000;

            // Mostramos por pantalla el tiempo que se ha seleccionado para la partida
            timeFormat(time, mp);

            // Al pulsar el botón comenzar partida
            start_button.setOnClickListener() {

                // Se desactiva el botón "Comenzar partida" y se activa el botón "Finalizar partida"
                start_button.visibility = View.GONE;
                finish_button.visibility = View.VISIBLE;

                // Se comienza a ejecutar la cuenta atrás. Al llegar a 0 se indica el mensaje de fin de partida.
                object : CountDownTimer(time, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        timeFormat(millisUntilFinished, mp);
                    }

                    override fun onFinish() {
                        countdown.textSize = 70F;
                        countdown.setTextColor(Color.GRAY);
                        countdown.text =  resources.getString(R.string.end_time);
                    }

                }.start();

            }

        };

        // Al pulsar el botón finalizar se lleva a una activity para la ronda de votación
        finish_button.setOnClickListener() {
            val intent = Intent(this, EndActivity::class.java);
            startActivity(intent);
            finish();
        }

    }

    // Traduce los milisegundos a minutos y segundos y le da formato
    private fun timeFormat(millis: Long, mp: MediaPlayer) {

        var minutes: Int = ((millis/1000)/60).toInt();
        var seconds: Int = ((millis/1000)%60).toInt();

        var counter = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        // Los últimos segundos el tiempo parpadea
        if ( minutes === 0 && seconds <= 10 && seconds % 2 == 0) {
            mp.start();
            countdown.setTextColor(Color.RED);
        } else {
            countdown.setTextColor(Color.GRAY);
        }

        countdown.text = counter;

    }
}