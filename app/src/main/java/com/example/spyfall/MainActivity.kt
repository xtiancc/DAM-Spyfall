package com.example.spyfall

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creamos la base de datos con ayuda de la clase DBHelper
        var helper = DBHelper(this);
        var db = helper.readableDatabase;

        // Se cierra puesto que no es necesaria en ningún punto de la actividads
        db.close();

        // Comenzar juego
        play_button.setOnClickListener() {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent);
        }

        // Abrir reglas del juego
        rules_button.setOnClickListener() {
            val intent = Intent(this, RulesActivity::class.java)
            startActivity(intent);
        }

        // Cerrar juego
        exit_button.setOnClickListener() {
            finish();
        }

    }


}