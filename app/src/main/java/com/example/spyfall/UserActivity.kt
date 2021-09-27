package com.example.spyfall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.spyfall.Models.PlayerModelClass
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    var helper = DBHelper(this);

    private lateinit var playerList: ArrayList<PlayerModelClass> ;
    private lateinit var playersView: ArrayList<String> ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        // Abrimos la base de datos
        var db = helper.readableDatabase;

        // Generamos la vista de jugadores
        generateList(helper);

        // La lista de jugadores, al hacer click sobre uno de los elementos podremos borrarlo
        lista_jugadores.setOnItemClickListener { parent, _, position, _ ->

            // Accedemos a la base de datos
            //var helper = DBHelper(this);
            //var db = helper.readableDatabase;

            // Recuperamos la posición que ha marcado el usuario
            val name = parent.getItemAtPosition(position).toString()

            // Creamos un mensaje de alerta para asegurarnos que el usuario quiere borrar el usuario de la lista
            val builder = AlertDialog.Builder(this);
            builder.setTitle(R.string.delete_title);
            val message = getString(R.string.delete_message, name.substringAfter(" "));
            builder.setMessage(message);

            // Si el usuario marca que si se borra de la base de datos y se muestra un mensaje de éxito
            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                db.delete("Players", "name = '${name.substringAfter(' ')}'", null );
                Toast.makeText(this, R.string.delete_ok, Toast.LENGTH_SHORT).show();
                // Actualizar la vista con los nuevos datos
                generateList(helper);
            }

            // Si el usuario marca la opción de cancelar se cierra la alerta
            builder.setNegativeButton(android.R.string.no) {dialog, which ->
            }

            // Mostrar el mensaje de alerta
            builder.show();

        }

        // Cambia a la actividad para agregar jugador
        add_button.setOnClickListener() {
            val intent = Intent(this, InsertUserActivity::class.java)
            startActivity(intent);
        }

        // Continuar configuración
        continue_button.setOnClickListener() {

            // De la base de datos se comprueba que haya tres o más registros (minimo para poder jugar)
            // Si hay tres o más jugadores cambia a la siguiente actividad, si no muestra un mensaje de error
            var playerCount = db.rawQuery("SELECT * FROM Players", null);
            if (playerCount.count >= 3) {
                val intent = Intent(this, ConfigureActivity::class.java)
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, R.string.next_ko, Toast.LENGTH_LONG).show();
            }

        }

        // Regresar al main
        return_button.setOnClickListener() {
            finish()
        }
    }

    // Cuando insertamos un jugador y volvemos a esta activity, se ejecuta onRestart.
    // IMPORTANTE actualizar la listView con los nuevos datos
    override fun onRestart() {
        super.onRestart();
        generateList(helper);
    }

    // Función que captura todos los registros de nuestra base de datos
    private fun generateList(helper: DBHelper) {

        // Acedemos a nuestra base de datos y buscamos todos los jugadores
        var db = helper.readableDatabase;
        var selectPlayers = db.rawQuery("SELECT * FROM Players", null);

        // Almacenamos los jugadores en un arraylist
        playerList = ArrayList();
        while(selectPlayers.moveToNext()) {
            var player = PlayerModelClass(
                selectPlayers.getString(0)
            );
            playerList.add(player);
        }

        // Llamamos al a función para completar la listview
        generateView();


    }

    // Función que monta la listview
    private fun generateView() {

        // Todos los elementos del array de jugadores los pasa a otro array y le incluye un index
        // De esta forma el usuario sabrá cuantos jugadores hay en total
        playersView = ArrayList();
        playerList.mapIndexed{index, it ->
            playersView.add("${index+1}. ${it.name}");
        };

        // Montamos el list
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, playersView);
        lista_jugadores.adapter = adapter;

    }

}