package com.example.spyfall

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.spyfall.Models.GameModelClass
import com.example.spyfall.Models.PlayerModelClass
import kotlinx.android.synthetic.main.activity_configure.*
import java.util.*

class ConfigureActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configure)

        // Creamos un sharedpreference que almacene por defecto el valor de 6
        var preferences: SharedPreferences = getSharedPreferences("configure", Context.MODE_PRIVATE);
        editTextTime.setText(preferences.getString("time", "6"));

        // Al pulsar sobre continuar
        continue_button.setOnClickListener() {

            // abrimos para editar el sharedpreference y se almacena un nuevo valor para time
            var editorPreference: SharedPreferences.Editor = preferences.edit();
            editorPreference.putString("time", editTextTime.text.toString());
            editorPreference.commit();

            // Cambiamos a la ventana para repartir rol
            val intent = Intent(this, RolActivity::class.java);
            startActivity(intent);

            // No permitimos que se regrese a un paso anterior
            finish();

        }

        // Regresar a la lista de usuarios
        return_button.setOnClickListener() {
            finish();
        }
    }

}