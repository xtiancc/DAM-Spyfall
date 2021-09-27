package com.example.spyfall

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.spyfall.Models.PlaceModelClass
import com.example.spyfall.Models.PlayerModelClass
import com.example.spyfall.Models.RolModelClass

class DBHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {

        // Se crean las tres tablas necesarias
        val createPlayers = "CREATE TABLE IF NOT EXISTS Players (name text PRIMARY KEY)";
        val createPlaces = "CREATE TABLE Places (id Integer PRIMARY KEY, place TEXT)";
        val createRoles = "CREATE TABLE Roles (id Integer PRIMARY KEY, rol TEXT, id_place int, FOREIGN KEY (id_place) REFERENCES Places(id))";

        db?.execSQL(createPlayers);
        db?.execSQL(createPlaces);
        db?.execSQL(createRoles);

        // Creamos un array con los datos necesarios y los almacenamos en la base de datos
        val players = arrayOf<PlayerModelClass> (
            PlayerModelClass("Rebeca"),
            PlayerModelClass("Marcos"),
            PlayerModelClass("Emma")
        );

        players.map{
            val cv = ContentValues();
            cv.put("name", it.name);
            db?.insert("Players", null, cv);
        };

        val places = arrayOf<PlaceModelClass> (

            PlaceModelClass(1, "Avión"),
            PlaceModelClass(2, "Banco"),
            PlaceModelClass(3, "Playa"),
            PlaceModelClass(4, "Catedral"),
            PlaceModelClass(5, "Casino"),
            PlaceModelClass(6, "Circo"),
            PlaceModelClass(7, "Fiesta de empresa"),
            PlaceModelClass(8, "Cruzada"),
            PlaceModelClass(9, "Centro de spa"),

            PlaceModelClass(10, "Embajada"),
            PlaceModelClass(11, "Hospital"),
            PlaceModelClass(12, "Hotel"),
            PlaceModelClass(13, "Base militar"),
            PlaceModelClass(14, "Estudio de cine"),
            PlaceModelClass(15, "Transatlántico"),
            PlaceModelClass(16, "Tren"),
            PlaceModelClass(17, "Barco pirata"),
            PlaceModelClass(18, "Estación polar"),

            PlaceModelClass(19, "Comisaría"),
            PlaceModelClass(20, "Restaurante"),
            PlaceModelClass(21, "Escuela"),
            PlaceModelClass(22, "Gasolinera"),
            PlaceModelClass(23, "Estación espacial"),
            PlaceModelClass(24, "Submarino"),
            PlaceModelClass(25, "Supermercado"),
            PlaceModelClass(26, "Teatro"),
            PlaceModelClass(27, "Universidad")

        );

        places.map{
            val cv = ContentValues();
            cv.put("id", it.id);
            cv.put("place", it.place);
            db?.insert("Places", null, cv);
        };

        val roles = arrayOf<RolModelClass> (

            RolModelClass(1, "Pasajero/a de primera clase", 1),
            RolModelClass(2, "Ingeniero/a de vuelo", 1),
            RolModelClass(3, "Especialista en mecánica", 1),
            RolModelClass(4, "Turista", 1),
            RolModelClass(5, "Auxiliar de vuelo", 1),
            RolModelClass(6, "Copiloto", 1),
            RolModelClass(7, "Comandante", 1),

            RolModelClass(8, "Piloto de furgón blindado", 2),
            RolModelClass(9, "Gerentee", 2),
            RolModelClass(10, "Asesor de hipotecas", 2),
            RolModelClass(11, "Delincuente", 2),
            RolModelClass(12, "Cliente", 2),
            RolModelClass(13, "Vigilante de seguridad", 2),
            RolModelClass(14, "Cajero/a", 2),

            RolModelClass(15, "Camarero/a", 3),
            RolModelClass(16, "Surfista", 3),
            RolModelClass(17, "Socorrista", 3),
            RolModelClass(18, "Carterista", 3),
            RolModelClass(19, "Bañista", 3),
            RolModelClass(20, "Paparazzi", 3),
            RolModelClass(21, "Heladero/a", 3),

            RolModelClass(22, "Cura", 4),
            RolModelClass(23, "Indigente", 4),
            RolModelClass(24, "Penitente", 4),
            RolModelClass(25, "Devoto/a", 4),
            RolModelClass(26, "Turista", 4),
            RolModelClass(27, "Monaguillo/a", 4),
            RolModelClass(28, "Corista", 4),

            RolModelClass(29, "Camarero/a", 5),
            RolModelClass(30, "Vigilante de seguridad", 5),
            RolModelClass(31, "Matón", 5),
            RolModelClass(32, "Jefe/a de sala", 5),
            RolModelClass(33, "Timador/a", 5),
            RolModelClass(34, "Crupier", 5),
            RolModelClass(35, "Apostante", 5),

            RolModelClass(36, "Acróbata", 6),
            RolModelClass(37, "Domador/a de leones", 6),
            RolModelClass(38, "Ilusionista", 6),
            RolModelClass(39, "Asistente", 6),
            RolModelClass(40, "Tragafuego", 6),
            RolModelClass(41, "Payaso", 6),
            RolModelClass(42, "Malabarista", 6),

            RolModelClass(43, "Pinchadiscos", 7),
            RolModelClass(44, "Gerente", 7),
            RolModelClass(45, "Aguafiestas", 7),
            RolModelClass(46, "Propietario", 7),
            RolModelClass(47, "Administrativo/a", 7),
            RolModelClass(48, "Contable", 7),
            RolModelClass(49, "Repartidor/a de comida", 7),

            RolModelClass(50, "Monje/a", 8),
            RolModelClass(51, "Preso", 8),
            RolModelClass(52, "Sirviente", 8),
            RolModelClass(53, "Obispo", 8),
            RolModelClass(54, "Escudero", 8),
            RolModelClass(55, "Arquero/a", 8),
            RolModelClass(56, "Jinete de guerra", 8),

            RolModelClass(57, "Cliente", 9),
            RolModelClass(58, "Estilista", 9),
            RolModelClass(59, "Masajista", 9),
            RolModelClass(60, "Manicurista", 9),
            RolModelClass(61, "Socorrista", 9),
            RolModelClass(62, "Esteticista", 9),
            RolModelClass(63, "Recepcionista", 9),



            RolModelClass(64, "Vigilante de seguridad", 10),
            RolModelClass(65, "Traductor/a", 10),
            RolModelClass(66, "Embajador/a", 10),
            RolModelClass(67, "Funcionario/a", 10),
            RolModelClass(68, "Turista", 10),
            RolModelClass(69, "Refugiado/a", 10),
            RolModelClass(70, "Representante del Estado", 10),

            RolModelClass(71, "Enfermero/a", 11),
            RolModelClass(72, "Doctor/a", 11),
            RolModelClass(73, "Anestesista", 11),
            RolModelClass(74, "Interino/a", 11),
            RolModelClass(75, "Paciente", 11),
            RolModelClass(76, "Terapeuta", 11),
            RolModelClass(77, "Cirujano/a", 11),

            RolModelClass(85, "Portero/a", 12),
            RolModelClass(86, "Vigilante de seguridad", 12),
            RolModelClass(87, "Gerente de hotel", 12),
            RolModelClass(88, "Gobernanta", 12),
            RolModelClass(89, "Huésped", 12),
            RolModelClass(90, "Camarero/a", 12),
            RolModelClass(91, "Asistente de habitaciones", 12),

            RolModelClass(120, "Cocinero/a", 13),
            RolModelClass(121, "Coronel", 13),
            RolModelClass(122, "Médico/a", 13),
            RolModelClass(123, "Soldado", 13),
            RolModelClass(124, "Francotirador/a", 13),
            RolModelClass(125, "Teniente Coronel", 13),
            RolModelClass(126, "Comandante", 13),

            RolModelClass(78, "Doble de acción", 14),
            RolModelClass(79, "Técnico/a de sonido", 14),
            RolModelClass(80, "Cámara", 14),
            RolModelClass(81, "Director/a", 14),
            RolModelClass(82, "Estilista de cine", 14),
            RolModelClass(83, "Intérprete", 14),
            RolModelClass(84, "Productor/a", 14),

            RolModelClass(92, "Turista millonario/a", 15),
            RolModelClass(93, "Cocinero/a", 15),
            RolModelClass(94, "Capitán/a", 15),
            RolModelClass(95, "Camarero/a", 15),
            RolModelClass(96, "Músico", 15),
            RolModelClass(97, "Personal de limpieza", 15),
            RolModelClass(98, "Operario/a naval", 15),

            RolModelClass(99, "Personal de mantenimiento", 16),
            RolModelClass(100, "Vigilante de seguridad", 16),
            RolModelClass(101, "Jefe/a de tren", 16),
            RolModelClass(102, "Maquinista", 16),
            RolModelClass(103, "Responsable de comunicación", 16),
            RolModelClass(104, "Recepcionista", 16),
            RolModelClass(105, "Turista", 16),

            RolModelClass(106, "Cocinero/a", 17),
            RolModelClass(107, "Navegante", 17),
            RolModelClass(108, "Carpintero", 17),
            RolModelClass(109, "Artillero/a", 17),
            RolModelClass(110, "Prisionero/a", 17),
            RolModelClass(111, "Músico", 17),
            RolModelClass(112, "Capitán/a", 17),

            RolModelClass(113, "Médico/a", 18),
            RolModelClass(114, "Geólogo", 18),
            RolModelClass(115, "Líder de expedición", 18),
            RolModelClass(116, "Biólogo/a", 18),
            RolModelClass(117, "Operador/a de comunicaciones", 18),
            RolModelClass(118, "Submarinista", 18),
            RolModelClass(119, "Meteorólogo/a", 18),



            RolModelClass(127, "Detective", 19),
            RolModelClass(128, "Abogado/a", 19),
            RolModelClass(129, "Periodista", 19),
            RolModelClass(130, "Forense", 19),
            RolModelClass(131, "Archivista", 19),
            RolModelClass(132, "Policía", 19),
            RolModelClass(133, "Delincuente", 19),

            RolModelClass(141, "Músico", 20),
            RolModelClass(142, "Cliente", 20),
            RolModelClass(143, "Camarero/a", 20),
            RolModelClass(144, "Ayudante de restauración", 20),
            RolModelClass(145, "Jefe/a de cocina", 20),
            RolModelClass(146, "Crítico gastronómico", 20),
            RolModelClass(147, "Asistente", 20),

            RolModelClass(162, "Profesor/a de gimnasia", 21),
            RolModelClass(163, "Estudiante", 21),
            RolModelClass(164, "Director/a", 21),
            RolModelClass(165, "Vigilante de seguridad", 21),
            RolModelClass(166, "Conserje", 21),
            RolModelClass(167, "Cocinero/a de comedor", 21),
            RolModelClass(168, "Responsable de mantenimiento", 21),

            RolModelClass(148, "Gerente", 22),
            RolModelClass(149, "Reponedor/a", 22),
            RolModelClass(150, "Motero/a", 22),
            RolModelClass(151, "Propietario de un coche", 22),
            RolModelClass(152, "Lavacoches", 22),
            RolModelClass(153, "Dependiente", 22),
            RolModelClass(154, "Mecánico/a", 22),

            RolModelClass(155, "Ingeniero/a", 23),
            RolModelClass(156, "Extraterreste", 23),
            RolModelClass(157, "Turista", 23),
            RolModelClass(158, "Astronauta", 23),
            RolModelClass(159, "Comandante", 23),
            RolModelClass(160, "Cientifíco/a", 23),
            RolModelClass(161, "Médico/a", 23),


            RolModelClass(134, "Cocinero/a", 24),
            RolModelClass(135, "Capitán/a", 24),
            RolModelClass(136, "Soldado", 24),
            RolModelClass(137, "Jefe/a de armas", 24),
            RolModelClass(138, "Marino/a", 24),
            RolModelClass(139, "Operador/a de comunicaciones", 24),
            RolModelClass(140, "Soldado", 24),

            RolModelClass(169, "Cliente", 25),
            RolModelClass(170, "Cajero/a", 25),
            RolModelClass(171, "Carninero/a", 25),
            RolModelClass(172, "Vigilante de seguridad", 25),
            RolModelClass(173, "Gerente", 25),
            RolModelClass(174, "Promotor/a de stand", 25),
            RolModelClass(175, "Reponedor/a", 25),

            RolModelClass(176, "Guardarropa", 26),
            RolModelClass(177, "Figurante", 26),
            RolModelClass(178, "Taquillero/a", 26),
            RolModelClass(179, "Asitente", 26),
            RolModelClass(180, "Director/a", 26),
            RolModelClass(181, "Intérprete", 26),
            RolModelClass(182, "Personal de iluminación", 26),

            RolModelClass(183, "Conserje", 27),
            RolModelClass(184, "Profesor/a", 27),
            RolModelClass(185, "Decano/a", 27),
            RolModelClass(186, "Psicologo/a", 27),
            RolModelClass(187, "Personal de mantenimiento", 27),
            RolModelClass(188, "Estudiante", 27),
            RolModelClass(189, "Tutor/a", 27)

        );

        roles.map{
            val cv = ContentValues();
            cv.put("id", it.id);
            cv.put("rol", it.rol);
            cv.put("id_place", it.id_place);
            db?.insert("Roles", null, cv);
        };

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Players no se modifica. Borraríamos datos insertados por el usuario
        db?.execSQL("DROP TABLE IF EXISTS Places");
        db?.execSQL("DROP TABLE IF EXISTS Roles");
        onCreate(db);
    }

    companion object {
        private const val DATABASE_NAME = "Spyfall";
        private const val DATABASE_VERSION = 1;
    }

}