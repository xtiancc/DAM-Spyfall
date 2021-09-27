package com.example.spyfall

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert_user.*
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.activity_user.continue_button
import kotlinx.android.synthetic.main.activity_user.return_button
import kotlinx.android.synthetic.main.activity_user.view.*

class InsertUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_user)

        // Al pulsar sobre el botón guardar
        continue_button.setOnClickListener() {

            // Se captura el texto del textview y se elimina los espacios
            var editName = editTextUser.text.toString().trim();

            // Abrimos nuestra base de datos
            var helper = DBHelper(this);
            var db = helper.readableDatabase;

            // Hacemos una busqueda de si existe un usuario con ese nombre
            var playerCount = db.rawQuery("SELECT * FROM Players WHERE name = '${editName}'", null);

            // Si no existe y la longitud de la cadena es superior a 0
            if (playerCount.count <= 0 && editName.isNotEmpty()) {

                // Creamos el usuario y lo insertamos en la base de datos
                val cv = ContentValues();
                cv.put("name", editName);
                db.insert("Players", null, cv);

                // IMPORTANTE: Cerrar nuestra base de datos
                db.close();

                // Mensaje por pantalla indicando que el usuario se ha agregado correctamente
                Toast.makeText(this, R.string.player_ok, Toast.LENGTH_SHORT).show();

                // Volvemos a la actividad anterior (UserActivity)
                finish();

            } else {

                // Si el usuario ya existe o la longitud es igual a 0, se muestra un mensaje de alerta al usuario
                Toast.makeText(this, R.string.player_ko, Toast.LENGTH_LONG).show();

            }

        }

        // Regresar al activity anterior
        return_button.setOnClickListener() {
            finish()
        }

    }

}