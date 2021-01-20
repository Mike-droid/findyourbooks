package com.itpn.findyourbooks

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class pantallaPrincipalPersona : AppCompatActivity() {

    lateinit var c:Conexion
    lateinit var rbLibro:RadioButton
    lateinit var rbAutor:RadioButton
    lateinit var rbTema:RadioButton
    lateinit var tvResultado:TextView
    lateinit var op:SQLiteDatabase
    lateinit var etBusqueda:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal_persona)

        c = Conexion(this,"biblioteca",null,1)

        //*****************************************************************//
        //insertarAutores()
        //insertarTemas()
        //insertarTiendas()
        //insertarLibros()
        // SOLO EJECUTAR ESAS FUNCIONES LA PRIMERA VEZ QUE SE CORRE LA APP
        //******************************************************************//

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intentPaypal = Intent(this,Paypal::class.java)
            startActivity(intentPaypal)
        }
    }

    //Menu de opciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Acciones para los items del menú de opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intentOpcion:Intent
        when(item.itemId){
            R.id.opcionTienda -> {intentOpcion = Intent(this,Tienda::class.java)
                startActivity(intentOpcion)}

            R.id.opcionContactar -> {intentOpcion = Intent(this,Contactanos::class.java)
                startActivity(intentOpcion)}
        }
        return super.onOptionsItemSelected(item)
    }

    //Función para buscar libros, autores o temas
    fun buscar(v:View){
        rbLibro = findViewById(R.id.rbLibro)
        rbAutor = findViewById(R.id.rbAutor)
        rbTema = findViewById(R.id.rbTema)
        tvResultado = findViewById(R.id.tvResultado)
        op = c.readableDatabase
        etBusqueda = findViewById(R.id.etBusqueda)
        var cursor:Cursor
        var resultado = ""

        //El resultado dependerá del radio button presionado
        when {
            rbLibro.isChecked -> {
                cursor = op.rawQuery("select titulo, nombre_autor, nombre_tienda from libro join autor on autor_idautor = idautor join tienda on tienda_idtienda = idtienda where titulo like '%${etBusqueda.text}%'",null)
                if (cursor.moveToFirst()){
                    do {
                        resultado += "Libro : ${cursor.getString(0)} \nAutor : ${cursor.getString(1)} \nTienda : ${cursor.getString(2)} \n\n"
                    }while (cursor.moveToNext())
                }
                tvResultado.text = resultado
                op.close()
            }
            rbAutor.isChecked -> {
                cursor = op.rawQuery("select nombre_autor,titulo from autor join libro on idautor = autor_idautor where nombre_autor like '%${etBusqueda.text}%'",null)
                if (cursor.moveToFirst()){
                    do {
                        resultado += "Autor : ${cursor.getString(0)} \nLibro : ${cursor.getString(1)} \n\n"
                    }while (cursor.moveToNext())
                }
                tvResultado.text = resultado
                op.close()
            }
            rbTema.isChecked -> {
                cursor = op.rawQuery("select nombre_tema,titulo from tema join libro on idtema = tema_idtema where nombre_tema like '%${etBusqueda.text}%'",null)
                if (cursor.moveToFirst()){
                    do {
                        resultado += "Tema : ${cursor.getString(0)} \nLibro : ${cursor.getString(1)} \n\n"
                    }while (cursor.moveToNext())
                }
                tvResultado.text = resultado
                op.close()
            }
            (!rbTema.isChecked) && (!rbAutor.isChecked) && (!rbLibro.isChecked) -> {
                Toast.makeText(this,"Una opción debe estar seleccionada",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun insertarAutores(){
        op = c.writableDatabase
        //Insertando nombres inventados de personas como autores
        op.execSQL("INSERT INTO autor (nombre_autor) VALUES (\"Conan Coleman\"),(\"Amery Zamora\"),(\"Herman Nieves\"),(\"Carl Hahn\"),(\"Mikayla Gray\"),(\"Tucker Trevino\"),(\"Griffin Bird\"),(\"Carlos Austin\"),(\"Yvette Cobb\"),(\"Paki Holmes\"),(\"Ivy Tran\"),(\"James Dennis\"),(\"Cally Palmer\"),(\"Raja Marquez\"),(\"Ulla Schmidt\"),(\"Macey Dyer\"),(\"Samson Melton\"),(\"Seth Hull\"),(\"Donna Hicks\"),(\"Urielle Gregory\"),(\"Mari Jimenez\"),(\"Kato Pickett\"),(\"Logan Dennis\"),(\"Ainsley Bray\"),(\"Beatrice Atkins\"),(\"Shana Joseph\"),(\"Celeste Silva\"),(\"Chaney Mcclure\"),(\"James Parker\"),(\"Anjolie Monroe\"),(\"Derek Head\"),(\"Alma Singleton\"),(\"Jerome Delaney\"),(\"Uta Fuller\"),(\"Fuller Roach\"),(\"Brooke Schneider\"),(\"Cruz Poole\"),(\"Honorato Bowman\"),(\"Kitra Leon\"),(\"Beau Velasquez\"),(\"Basia Price\"),(\"Ignatius Rivera\"),(\"Joan Erickson\"),(\"Paula Richardson\"),(\"Alika Kelley\"),(\"Kim Hayes\"),(\"Ezekiel Massey\"),(\"Uriah Ferrell\"),(\"Linda Macias\"),(\"Whitney Chase\"),(\"Lyle Mcgowan\"),(\"Dara Burris\"),(\"Matthew Wall\"),(\"Cassady Shepherd\"),(\"Melinda Frederick\"),(\"Lester Merrill\"),(\"Avram Griffin\"),(\"Shoshana Ryan\"),(\"Sierra Hart\"),(\"Felicia Ward\"),(\"Priscilla Crawford\"),(\"Jade Rocha\"),(\"Nevada Rosario\"),(\"Leigh Rios\"),(\"Rhonda Boone\"),(\"Kimberley Adkins\"),(\"Avram Collins\"),(\"Knox Grimes\"),(\"Basil Jefferson\"),(\"Farrah Stafford\"),(\"Xerxes Carroll\"),(\"Hedy Bray\"),(\"Alfonso Whitney\"),(\"Ray Hoover\"),(\"Melvin Graham\"),(\"Harding Mendoza\"),(\"Maryam Mcpherson\"),(\"Melvin Olson\"),(\"Buckminster Moon\"),(\"Jeremy Ray\"),(\"Daniel Wolfe\"),(\"Tanisha Browning\"),(\"Inga Buchanan\"),(\"Steven Walton\"),(\"Yael Whitfield\"),(\"Kirby Terry\"),(\"Palmer Zamora\"),(\"Eleanor Mills\"),(\"Ezekiel Berg\"),(\"Nadine Mcgowan\"),(\"Arden Hooper\"),(\"Arden Holt\"),(\"Phillip Swanson\"),(\"Jorden Garrison\"),(\"Florence Waters\"),(\"Kelly Jarvis\"),(\"Chandler Levine\"),(\"Fatima Riley\"),(\"Arthur Barber\"),(\"Maxwell Bradley\");")
        op.close()
    }

    fun insertarTemas(){
        op = c.writableDatabase
        //Insertando nombres inventados de temas de libros
        op.execSQL("INSERT INTO tema (nombre_tema) VALUES (\"Curabitur\"),(\"eleifend, nunc\"),(\"natoque\"),(\"dui.\"),(\"luctus ut,\"),(\"vitae\"),(\"sapien molestie\"),(\"imperdiet ullamcorper.\"),(\"Duis a\"),(\"diam\"),(\"eget nisi\"),(\"sodales purus,\"),(\"Phasellus elit\"),(\"nonummy ac,\"),(\"sed pede\"),(\"diam.\"),(\"mattis.\"),(\"vitae purus\"),(\"sed pede\"),(\"justo. Proin\"),(\"nonummy. Fusce\"),(\"luctus felis\"),(\"a,\"),(\"est.\"),(\"natoque\"),(\"lobortis augue\"),(\"natoque\"),(\"Proin eget\"),(\"id\"),(\"erat,\"),(\"vitae semper\"),(\"lobortis quam\"),(\"Nulla interdum.\"),(\"libero\"),(\"urna. Ut\"),(\"gravida sit\"),(\"rutrum\"),(\"massa. Vestibulum\"),(\"nunc\"),(\"eleifend.\"),(\"Aliquam erat\"),(\"suscipit,\"),(\"Curae;\"),(\"adipiscing ligula.\"),(\"Vivamus rhoncus.\"),(\"In\"),(\"eu, ultrices\"),(\"turpis. In\"),(\"libero. Proin\"),(\"nec,\"),(\"vehicula.\"),(\"vitae\"),(\"in\"),(\"lorem ipsum\"),(\"purus.\"),(\"cursus\"),(\"suscipit\"),(\"molestie tortor\"),(\"ipsum leo\"),(\"tellus.\"),(\"euismod\"),(\"tellus\"),(\"Aliquam\"),(\"et tristique\"),(\"egestas\"),(\"auctor\"),(\"eget ipsum.\"),(\"risus. Nunc\"),(\"Ut semper\"),(\"a\"),(\"facilisis lorem\"),(\"mollis lectus\"),(\"rutrum\"),(\"magna, malesuada\"),(\"non, dapibus\"),(\"et arcu\"),(\"Aenean\"),(\"Proin non\"),(\"enim. Nunc\"),(\"lorem\"),(\"Sed malesuada\"),(\"gravida non,\"),(\"lorem ipsum\"),(\"dui. Fusce\"),(\"pede, nonummy\"),(\"auctor,\"),(\"In faucibus.\"),(\"malesuada. Integer\"),(\"augue\"),(\"erat\"),(\"Phasellus nulla.\"),(\"magna\"),(\"a\"),(\"imperdiet\"),(\"lacus.\"),(\"inceptos\"),(\"lacus. Quisque\"),(\"Phasellus\"),(\"Curae; Phasellus\"),(\"bibendum ullamcorper.\");")
        op.close()
    }

    fun insertarLibros(){
        op = c.writableDatabase
        //Insertando nombres inventados para titulos de libros
        op.execSQL("INSERT INTO libro (titulo,tema_idtema,autor_idautor,tienda_idtienda) VALUES (\"Donec\",33,31,6),(\"erat\",36,33,2),(\"non sapien\",57,40,4),(\"vestibulum nec, euismod\",45,33,8),(\"purus. Duis elementum,\",62,33,3),(\"mauris\",36,7,10),(\"cursus et, magna.\",96,100,4),(\"Donec tempor, est\",7,85,3),(\"elementum, lorem ut\",79,66,6),(\"eu, ligula. Aenean\",92,23,3),(\"sit\",44,46,10),(\"Donec\",34,13,4),(\"Morbi metus.\",33,48,1),(\"Donec\",60,94,5),(\"hendrerit consectetuer, cursus\",83,61,8),(\"dapibus\",4,56,10),(\"laoreet\",40,94,5),(\"Maecenas libero\",25,59,3),(\"lorem vitae odio\",50,23,3),(\"ullamcorper,\",88,22,9),(\"eu neque pellentesque\",69,7,6),(\"mollis\",1,22,2),(\"enim, condimentum eget,\",92,56,5),(\"pharetra. Quisque ac\",7,14,3),(\"arcu. Morbi\",11,47,5),(\"nisi magna\",91,34,1),(\"nunc nulla vulputate\",45,39,5),(\"a,\",74,73,6),(\"Sed nec metus\",11,43,2),(\"scelerisque\",17,92,2),(\"Pellentesque\",26,95,3),(\"lacus. Quisque imperdiet,\",93,60,7),(\"quis,\",16,71,3),(\"natoque\",53,47,10),(\"vel nisl. Quisque\",31,90,2),(\"molestie.\",70,22,7),(\"leo,\",53,51,3),(\"eu\",49,11,2),(\"eu, eleifend nec,\",83,48,8),(\"facilisi.\",88,81,4),(\"vestibulum\",96,36,1),(\"dolor egestas rhoncus.\",82,55,8),(\"semper\",4,85,2),(\"eget, volutpat\",22,12,6),(\"Praesent eu dui.\",56,16,4),(\"nec\",30,71,3),(\"sed dui. Fusce\",36,20,7),(\"augue\",96,80,9),(\"diam luctus lobortis.\",27,59,3),(\"natoque penatibus\",8,64,5),(\"libero mauris, aliquam\",75,27,8),(\"Nam interdum\",69,50,7),(\"molestie arcu.\",11,46,9),(\"quam.\",65,44,1),(\"vitae,\",61,18,2),(\"aliquam\",81,91,10),(\"mi,\",86,15,9),(\"vitae odio sagittis\",45,69,9),(\"parturient montes, nascetur\",97,10,10),(\"pellentesque massa\",57,58,6),(\"amet lorem\",5,44,9),(\"turpis egestas.\",1,19,5),(\"dapibus ligula.\",62,95,6),(\"a\",100,81,5),(\"euismod ac,\",26,49,4),(\"diam dictum sapien.\",98,22,6),(\"bibendum.\",77,50,8),(\"Maecenas\",27,58,10),(\"dictum sapien. Aenean\",93,40,7),(\"lacus.\",87,59,9),(\"libero.\",44,22,1),(\"pretium aliquet, metus\",73,71,9),(\"Nulla tincidunt, neque\",87,68,6),(\"ridiculus mus.\",67,75,3),(\"Aliquam\",62,51,1),(\"justo nec ante.\",83,62,2),(\"magna sed dui.\",42,67,4),(\"porttitor tellus\",87,12,6),(\"egestas.\",81,7,9),(\"Ut sagittis\",33,99,2),(\"amet\",9,15,10),(\"faucibus lectus,\",73,66,5),(\"a, facilisis non,\",39,97,7),(\"et\",89,81,2),(\"elementum purus,\",52,5,6),(\"in, tempus\",23,46,1),(\"sed, sapien. Nunc\",99,53,6),(\"tristique\",64,81,2),(\"vitae, erat.\",72,98,8),(\"non, vestibulum nec,\",20,57,8),(\"Duis at lacus.\",41,9,10),(\"dignissim. Maecenas\",79,53,9),(\"nunc. In\",20,10,9),(\"tellus,\",56,49,5),(\"turpis. Aliquam adipiscing\",55,68,1),(\"facilisis\",54,76,10),(\"ut, nulla. Cras\",57,34,6),(\"Integer in magna.\",90,48,9),(\"lacus. Cras interdum.\",45,37,1),(\"mollis.\",18,38,4);")
        op.close()
    }

    fun insertarTiendas(){
        op = c.writableDatabase
        //Insertando nombres y direcciones inventadas para las tiendas
        op.execSQL("INSERT INTO tienda (nombre_tienda,direccion) VALUES (\"Quis Company\",\"P.O. Box 789, 9341 Donec Av.\"),(\"Dictum Cursus Industries\",\"P.O. Box 934, 4864 Nullam Street\"),(\"Aliquet Lobortis Nisi Foundation\",\"P.O. Box 482, 2290 Urna Rd.\"),(\"Nunc In At LLP\",\"P.O. Box 865, 284 Feugiat St.\"),(\"Iaculis Enim Sit Foundation\",\"Ap #317-4808 Nunc. St.\"),(\"Pede Ultrices A LLP\",\"Ap #958-7901 Tellus Road\"),(\"Proin Inc.\",\"708-3914 Montes, Rd.\"),(\"Sociis Natoque Inc.\",\"P.O. Box 888, 6390 Luctus Rd.\"),(\"Ornare Elit LLP\",\"Ap #485-2961 Accumsan St.\"),(\"Elit PC\",\"Ap #710-3388 Magnis Rd.\"),(\"At Ltd\",\"P.O. Box 518, 4340 Molestie Road\"),(\"Nulla Tincidunt PC\",\"Ap #526-4429 Eget Road\"),(\"Vitae Sodales Associates\",\"252-4781 Convallis Ave\"),(\"Arcu Inc.\",\"P.O. Box 460, 8278 Erat. Ave\"),(\"Risus Donec Nibh PC\",\"226-1079 Nonummy Ave\"),(\"Erat Volutpat Nulla Consulting\",\"Ap #135-2548 Hendrerit Avenue\"),(\"In Ornare Sagittis Industries\",\"8800 Auctor Ave\"),(\"Eros Nec Tellus LLP\",\"408-8475 Quis Avenue\"),(\"Enim Consequat Ltd\",\"P.O. Box 696, 5405 Cras Rd.\"),(\"Nascetur Institute\",\"Ap #961-2226 Sit Road\"),(\"Diam Luctus Lobortis Limited\",\"P.O. Box 128, 8446 Nullam St.\"),(\"Purus Corp.\",\"P.O. Box 117, 6679 Suspendisse Ave\"),(\"Ac Ltd\",\"378-9037 A St.\"),(\"Class Aptent Taciti Corporation\",\"P.O. Box 749, 3303 Laoreet St.\"),(\"Auctor Incorporated\",\"Ap #675-6481 Dignissim. Street\"),(\"Erat Vivamus Corporation\",\"Ap #590-9741 Amet, Ave\"),(\"Dignissim Maecenas LLC\",\"P.O. Box 185, 5261 Dolor Ave\"),(\"Magna Et Ipsum Institute\",\"137-5027 Nam Street\"),(\"Augue Sed Company\",\"P.O. Box 775, 6800 Nulla St.\"),(\"Id Risus Institute\",\"P.O. Box 838, 7163 Sit Av.\"),(\"Mi Lorem PC\",\"P.O. Box 587, 9908 Aliquam Road\"),(\"Dictum Placerat Augue LLC\",\"P.O. Box 791, 8913 A, St.\"),(\"Pharetra Inc.\",\"Ap #302-8524 Commodo Av.\"),(\"Ornare LLP\",\"P.O. Box 599, 4086 Augue Ave\"),(\"Urna Institute\",\"3678 Sed Rd.\"),(\"Tempor Diam Industries\",\"564-3996 Ipsum Avenue\"),(\"Rutrum Associates\",\"601-370 Fermentum Ave\"),(\"Massa Mauris Vestibulum Corporation\",\"P.O. Box 901, 108 Curae; St.\"),(\"Nulla Company\",\"Ap #298-5570 Sed Ave\"),(\"Pellentesque Tellus PC\",\"Ap #864-714 Lobortis Street\"),(\"Blandit Viverra Company\",\"P.O. Box 992, 1642 Cras Rd.\"),(\"Lacus Corporation\",\"P.O. Box 977, 8869 Proin Avenue\"),(\"Nisi A Odio Inc.\",\"6607 Enim Ave\"),(\"Sit Amet Massa Consulting\",\"P.O. Box 887, 8761 A Av.\"),(\"Justo Company\",\"Ap #972-2939 Tellus. Rd.\"),(\"Nunc Sed Orci Corporation\",\"Ap #137-2673 Nunc St.\"),(\"Sit Industries\",\"984-4340 Donec Av.\"),(\"Molestie Sodales Mauris Foundation\",\"1368 Cubilia Road\"),(\"Nunc Foundation\",\"P.O. Box 179, 936 Metus Av.\"),(\"Arcu Nunc Ltd\",\"P.O. Box 155, 3020 Dolor, Road\"),(\"Facilisis Non Bibendum Incorporated\",\"P.O. Box 930, 2645 Et St.\"),(\"Orci Quis Associates\",\"8294 Nunc Ave\"),(\"Ante Bibendum Ullamcorper Corporation\",\"347-5497 At, Street\"),(\"Mattis Semper Dui Foundation\",\"Ap #603-8590 Ipsum. Rd.\"),(\"Nec Institute\",\"P.O. Box 601, 6821 Egestas Av.\"),(\"Amet Metus Aliquam Foundation\",\"P.O. Box 279, 4054 Vestibulum Ave\"),(\"Aliquet Libero LLC\",\"P.O. Box 674, 8487 Metus Ave\"),(\"Faucibus Lectus A Inc.\",\"8498 Arcu. Street\"),(\"Eu Erat Corp.\",\"8439 Mauris. Road\"),(\"A Incorporated\",\"Ap #927-8887 Pede Road\"),(\"Gravida Non Sollicitudin Associates\",\"389-4430 Duis Ave\"),(\"Egestas Aliquam Associates\",\"573-677 Morbi Av.\"),(\"Lobortis Tellus Justo Corp.\",\"P.O. Box 692, 4701 Dui. St.\"),(\"Vestibulum Associates\",\"617-4245 Feugiat Rd.\"),(\"Fusce Diam Nunc Company\",\"P.O. Box 864, 5332 Cubilia Street\"),(\"Tincidunt PC\",\"6012 Elit. Rd.\"),(\"Varius Et Euismod Incorporated\",\"972-6797 Primis Avenue\"),(\"In Tempus Ltd\",\"890-7030 Dui. Road\"),(\"Nam LLC\",\"P.O. Box 439, 1357 Orci St.\"),(\"Bibendum Ullamcorper Duis Company\",\"414-6278 Faucibus St.\"),(\"Et Magnis PC\",\"169 Aliquam, St.\"),(\"Dui LLC\",\"1084 Penatibus Ave\"),(\"Nunc Mauris Morbi Foundation\",\"P.O. Box 695, 6647 Mauris St.\"),(\"Mauris Company\",\"P.O. Box 144, 6013 Euismod Av.\"),(\"Amet Risus Donec PC\",\"P.O. Box 203, 8283 Quam, Rd.\"),(\"Vulputate PC\",\"719-473 Faucibus Rd.\"),(\"Hymenaeos Mauris Ut PC\",\"3180 Luctus Ave\"),(\"Bibendum LLC\",\"Ap #604-6528 Mauris St.\"),(\"Rutrum Corp.\",\"P.O. Box 823, 5261 Vehicula Avenue\"),(\"Elit Aliquam Auctor Corp.\",\"Ap #443-5879 Donec Rd.\"),(\"Donec Dignissim Institute\",\"Ap #120-707 Ipsum. St.\"),(\"Lacinia Mattis Integer Ltd\",\"Ap #328-3085 Dui. Av.\"),(\"Lacus Aliquam LLP\",\"Ap #494-5521 Cras Ave\"),(\"Convallis Ligula Donec Incorporated\",\"P.O. Box 369, 7634 Pellentesque Street\"),(\"Mauris Magna Institute\",\"9607 Nulla. Rd.\"),(\"Non Dapibus Rutrum Inc.\",\"3800 Ut, Ave\"),(\"A Enim Suspendisse Incorporated\",\"Ap #462-9995 Eu Street\"),(\"Arcu Nunc LLC\",\"940 Integer Ave\"),(\"Lobortis Corporation\",\"316 Metus Av.\"),(\"Eget Corp.\",\"128-8019 Sem Rd.\"),(\"Urna Nunc Quis LLC\",\"403-6916 Sed St.\"),(\"Elit LLP\",\"Ap #480-880 Nulla. Rd.\"),(\"Ac Sem Ut Incorporated\",\"Ap #677-6819 Enim. Rd.\"),(\"In Consulting\",\"Ap #927-2391 Et St.\"),(\"Donec Consectetuer PC\",\"P.O. Box 398, 6878 Adipiscing Road\"),(\"Donec Dignissim Magna Corporation\",\"590-9561 Pharetra. St.\"),(\"Eleifend Incorporated\",\"1901 Tellus. Avenue\"),(\"Nec Mauris Blandit Industries\",\"870-2690 Malesuada Rd.\"),(\"Tincidunt Aliquam Limited\",\"1653 Pretium Rd.\");")
        op.close()
    }

}