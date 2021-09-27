package com.example.spyfall

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.spyfall.Models.GameModelClass
import kotlinx.android.synthetic.main.activity_rol.*
import java.util.*
import kotlin.collections.ArrayList

class RolActivity : AppCompatActivity() {

    private lateinit var playersArray: ArrayList<GameModelClass>;
    private lateinit var rolList: ArrayList<String> ;
    private var helper = DBHelper(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rol)

        // Llamamos a la función para completar el array de jugadores
        completeArray(helper);

        // Una vez tenemos el array cogemos los datos del primero
        showData(playersArray.first());

        // Al pulsar sobre el botón revelar, aparece el rol y la ubicación (en caso de que no sea el espía)
        reveal_button.setOnClickListener() {
            reveal_button.visibility = View.GONE;
            TextViewPlace.visibility = View.VISIBLE;
            TextViewRol.visibility = View.VISIBLE;
            continue_button.visibility = View.VISIBLE;
        }

        continue_button.setOnClickListener() {

            // Al pulsar sobre el botón continuar se elimina los datos del primer jugador
            playersArray.removeAt(0);

            // Si el array todavía tiene más jugadores vuelve a repetir el proceso
            // Cuando no quedan más jugadores pasa al siguiente activity para dar comienzo a la partida
            if (playersArray.size > 0 ) {

                showData(playersArray.first());

            } else {

                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent);
                finish();

            }

        }
    }

    private fun completeArray(helper: DBHelper) {

        // 1. Obtener nombres de los jugadores
        var db = helper.readableDatabase;
        var selectPlayers = db.rawQuery("SELECT * FROM Players", null);

        // 2. Obtener un numero al azar y buscar con ese el id de la localización unificado a los roles.
        var placesList = db.rawQuery("SELECT * FROM Places", null);
        var randomNumber = randomFunction(placesList.count);
        var selectedPlace = randomizePlace(helper, randomNumber);

        // 3. Obtener todos los roles de dicha localización
        var rolesList = db.rawQuery("SELECT rol FROM Roles WHERE id_place=${randomNumber}", null);

        rolList = ArrayList();
        while(rolesList.moveToNext()) {
            rolList.add(rolesList.getString(0));
        }

        // 4. Seleccionar un numero al azar para poner ese jugador como espia.
        var randomSpy = randomFunction(selectPlayers.count);

        // 5. Recorremos el arrayList
        playersArray = ArrayList();
        var count = 1;
        while(selectPlayers.moveToNext()) {

            // Si el contador coincide con el número al azar del paso 4, se le adjudica el rol de espía en lugar de la localización
            // Si el contador no coincide con el número al azar del paso 4, se le adjudica la localización y un rol del paso 3
            var player: GameModelClass = if (count === randomSpy) {
                GameModelClass(
                    "${selectPlayers.getString(0)}",
                    "${resources.getString(R.string.spy)}",
                    "${resources.getString(R.string.secret_spy)}"
                );
            } else {

                var randomRol = randomFunction(rolesList.count);

                GameModelClass(
                    "${selectPlayers.getString(0)}",
                    "${selectedPlace}",
                    "${rolList[randomRol]}"
                );
            }

            playersArray.add(player);
            count ++;

        }

        db.close();

    }

    private fun randomizePlace(helper: DBHelper, randomNumber: Int): String? {

        var db = helper.readableDatabase;
        var place: String? = null;

        var placeInfo = db.rawQuery("SELECT place FROM Places WHERE id=${randomNumber}", null);

        while(placeInfo.moveToNext()) {
            place = placeInfo.getString(0);
        }

        return place;
    }

    private fun randomFunction(max: Int): Int {
        return Random().nextInt((max - 1)) + 1;
    }

    private fun showData(player: GameModelClass) {

        reveal_button.visibility = View.VISIBLE;
        TextViewPlace.visibility = View.GONE;
        TextViewRol.visibility = View.GONE;
        continue_button.visibility = View.GONE;

        TextViewName.text = player.name;
        TextViewPlace.text = player.place;
        TextViewRol.text = player.rol;
    }

}