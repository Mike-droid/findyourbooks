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
import com.google.android.material.snackbar.Snackbar

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
        //insertarLibros()
        //insertarTiendas()
        // SOLO EJECUTAR ESAS FUNCIONES LA PRIMERA VEZ QUE SE CORRE LA APP
        //******************************************************************//

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }
    }

    //Menu de opciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Acciones para los items del menú de opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        intent = Intent(this,Tienda::class.java)
        when(item.itemId){
            R.id.opcionTienda -> startActivity(intent)
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
                cursor = op.rawQuery("select titulo, nombre_autor from libro join autor on autor_idautor = idautor where titulo like '%${etBusqueda.text}%'",null)
                if (cursor.moveToFirst()){
                    do {
                        resultado += "Libro: ${cursor.getString(0)} , autor: ${cursor.getString(1)} \n\n"
                    }while (cursor.moveToNext())
                }
                tvResultado.text = resultado
                op.close()
            }
            rbAutor.isChecked -> {
                cursor = op.rawQuery("select nombre_autor,titulo from autor join libro on idautor = autor_idautor where nombre_autor like '%${etBusqueda.text}%'",null)
                if (cursor.moveToFirst()){
                    do {
                        resultado += "Autor : ${cursor.getString(0)}, libro: ${cursor.getString(1)} \n\n"
                    }while (cursor.moveToNext())
                }
                tvResultado.text = resultado
                op.close()
            }
            rbTema.isChecked -> {
                cursor = op.rawQuery("select nombre_tema,titulo from tema join libro on idtema = tema_idtema where nombre_tema like '%${etBusqueda.text}%'",null)
                if (cursor.moveToFirst()){
                    do {
                        resultado += "Tema : ${cursor.getString(0)}, libro: ${cursor.getString(1)} \n\n"
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
        op.execSQL("INSERT INTO libro (titulo,autor_idautor,tema_idtema) VALUES (\"malesuada augue ut lacus. Nulla\",52,13),(\"sem magna nec quam. Curabitur\",80,25),(\"tincidunt, neque vitae semper egestas,\",29,72),(\"enim. Etiam imperdiet dictum magna.\",99,77),(\"semper et, lacinia vitae, sodales\",5,63),(\"nunc est, mollis non, cursus\",56,96),(\"eu dolor egestas rhoncus. Proin\",56,43),(\"rutrum eu, ultrices sit amet,\",59,29),(\"purus, in molestie tortor nibh\",7,43),(\"mollis. Integer tincidunt aliquam arcu.\",52,68),(\"Integer urna. Vivamus molestie dapibus\",82,85),(\"quam. Curabitur vel lectus. Cum\",2,63),(\"mauris elit, dictum eu, eleifend\",75,46),(\"ultrices, mauris ipsum porta elit,\",99,30),(\"risus quis diam luctus lobortis.\",31,65),(\"amet metus. Aliquam erat volutpat.\",3,50),(\"lacus. Quisque imperdiet, erat nonummy\",8,83),(\"neque non quam. Pellentesque habitant\",35,1),(\"ante. Vivamus non lorem vitae\",84,92),(\"facilisis non, bibendum sed, est.\",31,26),(\"non, vestibulum nec, euismod in,\",43,23),(\"dui augue eu tellus. Phasellus\",24,35),(\"risus. Donec egestas. Duis ac\",75,97),(\"Aliquam nisl. Nulla eu neque\",20,3),(\"natoque penatibus et magnis dis\",72,91),(\"purus mauris a nunc. In\",37,44),(\"est. Nunc ullamcorper, velit in\",39,48),(\"aliquet magna a neque. Nullam\",78,50),(\"odio. Aliquam vulputate ullamcorper magna.\",70,57),(\"vitae, orci. Phasellus dapibus quam\",77,25),(\"bibendum fermentum metus. Aenean sed\",57,14),(\"vulputate, posuere vulputate, lacus. Cras\",53,92),(\"eleifend, nunc risus varius orci,\",71,26),(\"Nulla eu neque pellentesque massa\",21,76),(\"morbi tristique senectus et netus\",55,88),(\"tincidunt vehicula risus. Nulla eget\",81,82),(\"Fusce fermentum fermentum arcu. Vestibulum\",82,33),(\"et malesuada fames ac turpis\",78,55),(\"arcu. Vestibulum ante ipsum primis\",93,13),(\"tempus scelerisque, lorem ipsum sodales\",59,93),(\"augue. Sed molestie. Sed id\",16,76),(\"semper et, lacinia vitae, sodales\",18,93),(\"Nullam velit dui, semper et,\",46,73),(\"turpis. In condimentum. Donec at\",34,34),(\"rhoncus. Proin nisl sem, consequat\",75,2),(\"Cras vulputate velit eu sem.\",41,18),(\"nascetur ridiculus mus. Proin vel\",61,43),(\"Nam interdum enim non nisi.\",14,16),(\"Aenean eget metus. In nec\",56,18),(\"fermentum risus, at fringilla purus\",54,54),(\"odio semper cursus. Integer mollis.\",68,81),(\"iaculis odio. Nam interdum enim\",58,93),(\"id sapien. Cras dolor dolor,\",40,62),(\"viverra. Maecenas iaculis aliquet diam.\",78,96),(\"ante dictum cursus. Nunc mauris\",78,96),(\"quis lectus. Nullam suscipit, est\",13,76),(\"erat semper rutrum. Fusce dolor\",39,91),(\"at pede. Cras vulputate velit\",47,71),(\"aliquet nec, imperdiet nec, leo.\",8,16),(\"eros non enim commodo hendrerit.\",10,94),(\"cursus. Nunc mauris elit, dictum\",52,97),(\"Class aptent taciti sociosqu ad\",65,57),(\"metus. Aliquam erat volutpat. Nulla\",20,38),(\"ullamcorper viverra. Maecenas iaculis aliquet\",81,32),(\"Etiam ligula tortor, dictum eu,\",50,42),(\"lobortis quam a felis ullamcorper\",50,57),(\"fringilla euismod enim. Etiam gravida\",61,75),(\"vulputate ullamcorper magna. Sed eu\",87,69),(\"vitae, posuere at, velit. Cras\",27,11),(\"malesuada. Integer id magna et\",52,66),(\"sem, consequat nec, mollis vitae,\",64,33),(\"posuere at, velit. Cras lorem\",65,34),(\"fermentum arcu. Vestibulum ante ipsum\",79,11),(\"eleifend nec, malesuada ut, sem.\",69,49),(\"purus sapien, gravida non, sollicitudin\",62,43),(\"ante bibendum ullamcorper. Duis cursus,\",99,94),(\"Sed malesuada augue ut lacus.\",97,98),(\"sodales at, velit. Pellentesque ultricies\",55,100),(\"Suspendisse dui. Fusce diam nunc,\",4,20),(\"facilisis lorem tristique aliquet. Phasellus\",67,75),(\"sed dui. Fusce aliquam, enim\",20,25),(\"Donec nibh. Quisque nonummy ipsum\",41,85),(\"aliquet nec, imperdiet nec, leo.\",27,29),(\"luctus vulputate, nisi sem semper\",62,45),(\"mauris eu elit. Nulla facilisi.\",50,57),(\"rutrum lorem ac risus. Morbi\",79,84),(\"pretium aliquet, metus urna convallis\",99,97),(\"eget, volutpat ornare, facilisis eget,\",27,42),(\"justo sit amet nulla. Donec\",8,79),(\"morbi tristique senectus et netus\",15,86),(\"blandit enim consequat purus. Maecenas\",4,24),(\"enim diam vel arcu. Curabitur\",76,83),(\"ullamcorper eu, euismod ac, fermentum\",19,14),(\"cursus et, magna. Praesent interdum\",22,66),(\"condimentum. Donec at arcu. Vestibulum\",5,52),(\"id risus quis diam luctus\",38,74),(\"mauris a nunc. In at\",3,59),(\"libero. Integer in magna. Phasellus\",17,6),(\"vitae, erat. Vivamus nisi. Mauris\",55,90),(\"Aliquam rutrum lorem ac risus.\",52,8);")
        op.close()
    }

    fun insertarTiendas(){
        op = c.writableDatabase
        //Insertando nombres y direcciones inventadas para las tiendas
        op.execSQL("INSERT INTO tienda (nombre_tienda,direccion) VALUES (\"Quis Company\",\"P.O. Box 789, 9341 Donec Av.\"),(\"Dictum Cursus Industries\",\"P.O. Box 934, 4864 Nullam Street\"),(\"Aliquet Lobortis Nisi Foundation\",\"P.O. Box 482, 2290 Urna Rd.\"),(\"Nunc In At LLP\",\"P.O. Box 865, 284 Feugiat St.\"),(\"Iaculis Enim Sit Foundation\",\"Ap #317-4808 Nunc. St.\"),(\"Pede Ultrices A LLP\",\"Ap #958-7901 Tellus Road\"),(\"Proin Inc.\",\"708-3914 Montes, Rd.\"),(\"Sociis Natoque Inc.\",\"P.O. Box 888, 6390 Luctus Rd.\"),(\"Ornare Elit LLP\",\"Ap #485-2961 Accumsan St.\"),(\"Elit PC\",\"Ap #710-3388 Magnis Rd.\"),(\"At Ltd\",\"P.O. Box 518, 4340 Molestie Road\"),(\"Nulla Tincidunt PC\",\"Ap #526-4429 Eget Road\"),(\"Vitae Sodales Associates\",\"252-4781 Convallis Ave\"),(\"Arcu Inc.\",\"P.O. Box 460, 8278 Erat. Ave\"),(\"Risus Donec Nibh PC\",\"226-1079 Nonummy Ave\"),(\"Erat Volutpat Nulla Consulting\",\"Ap #135-2548 Hendrerit Avenue\"),(\"In Ornare Sagittis Industries\",\"8800 Auctor Ave\"),(\"Eros Nec Tellus LLP\",\"408-8475 Quis Avenue\"),(\"Enim Consequat Ltd\",\"P.O. Box 696, 5405 Cras Rd.\"),(\"Nascetur Institute\",\"Ap #961-2226 Sit Road\"),(\"Diam Luctus Lobortis Limited\",\"P.O. Box 128, 8446 Nullam St.\"),(\"Purus Corp.\",\"P.O. Box 117, 6679 Suspendisse Ave\"),(\"Ac Ltd\",\"378-9037 A St.\"),(\"Class Aptent Taciti Corporation\",\"P.O. Box 749, 3303 Laoreet St.\"),(\"Auctor Incorporated\",\"Ap #675-6481 Dignissim. Street\"),(\"Erat Vivamus Corporation\",\"Ap #590-9741 Amet, Ave\"),(\"Dignissim Maecenas LLC\",\"P.O. Box 185, 5261 Dolor Ave\"),(\"Magna Et Ipsum Institute\",\"137-5027 Nam Street\"),(\"Augue Sed Company\",\"P.O. Box 775, 6800 Nulla St.\"),(\"Id Risus Institute\",\"P.O. Box 838, 7163 Sit Av.\"),(\"Mi Lorem PC\",\"P.O. Box 587, 9908 Aliquam Road\"),(\"Dictum Placerat Augue LLC\",\"P.O. Box 791, 8913 A, St.\"),(\"Pharetra Inc.\",\"Ap #302-8524 Commodo Av.\"),(\"Ornare LLP\",\"P.O. Box 599, 4086 Augue Ave\"),(\"Urna Institute\",\"3678 Sed Rd.\"),(\"Tempor Diam Industries\",\"564-3996 Ipsum Avenue\"),(\"Rutrum Associates\",\"601-370 Fermentum Ave\"),(\"Massa Mauris Vestibulum Corporation\",\"P.O. Box 901, 108 Curae; St.\"),(\"Nulla Company\",\"Ap #298-5570 Sed Ave\"),(\"Pellentesque Tellus PC\",\"Ap #864-714 Lobortis Street\"),(\"Blandit Viverra Company\",\"P.O. Box 992, 1642 Cras Rd.\"),(\"Lacus Corporation\",\"P.O. Box 977, 8869 Proin Avenue\"),(\"Nisi A Odio Inc.\",\"6607 Enim Ave\"),(\"Sit Amet Massa Consulting\",\"P.O. Box 887, 8761 A Av.\"),(\"Justo Company\",\"Ap #972-2939 Tellus. Rd.\"),(\"Nunc Sed Orci Corporation\",\"Ap #137-2673 Nunc St.\"),(\"Sit Industries\",\"984-4340 Donec Av.\"),(\"Molestie Sodales Mauris Foundation\",\"1368 Cubilia Road\"),(\"Nunc Foundation\",\"P.O. Box 179, 936 Metus Av.\"),(\"Arcu Nunc Ltd\",\"P.O. Box 155, 3020 Dolor, Road\"),(\"Facilisis Non Bibendum Incorporated\",\"P.O. Box 930, 2645 Et St.\"),(\"Orci Quis Associates\",\"8294 Nunc Ave\"),(\"Ante Bibendum Ullamcorper Corporation\",\"347-5497 At, Street\"),(\"Mattis Semper Dui Foundation\",\"Ap #603-8590 Ipsum. Rd.\"),(\"Nec Institute\",\"P.O. Box 601, 6821 Egestas Av.\"),(\"Amet Metus Aliquam Foundation\",\"P.O. Box 279, 4054 Vestibulum Ave\"),(\"Aliquet Libero LLC\",\"P.O. Box 674, 8487 Metus Ave\"),(\"Faucibus Lectus A Inc.\",\"8498 Arcu. Street\"),(\"Eu Erat Corp.\",\"8439 Mauris. Road\"),(\"A Incorporated\",\"Ap #927-8887 Pede Road\"),(\"Gravida Non Sollicitudin Associates\",\"389-4430 Duis Ave\"),(\"Egestas Aliquam Associates\",\"573-677 Morbi Av.\"),(\"Lobortis Tellus Justo Corp.\",\"P.O. Box 692, 4701 Dui. St.\"),(\"Vestibulum Associates\",\"617-4245 Feugiat Rd.\"),(\"Fusce Diam Nunc Company\",\"P.O. Box 864, 5332 Cubilia Street\"),(\"Tincidunt PC\",\"6012 Elit. Rd.\"),(\"Varius Et Euismod Incorporated\",\"972-6797 Primis Avenue\"),(\"In Tempus Ltd\",\"890-7030 Dui. Road\"),(\"Nam LLC\",\"P.O. Box 439, 1357 Orci St.\"),(\"Bibendum Ullamcorper Duis Company\",\"414-6278 Faucibus St.\"),(\"Et Magnis PC\",\"169 Aliquam, St.\"),(\"Dui LLC\",\"1084 Penatibus Ave\"),(\"Nunc Mauris Morbi Foundation\",\"P.O. Box 695, 6647 Mauris St.\"),(\"Mauris Company\",\"P.O. Box 144, 6013 Euismod Av.\"),(\"Amet Risus Donec PC\",\"P.O. Box 203, 8283 Quam, Rd.\"),(\"Vulputate PC\",\"719-473 Faucibus Rd.\"),(\"Hymenaeos Mauris Ut PC\",\"3180 Luctus Ave\"),(\"Bibendum LLC\",\"Ap #604-6528 Mauris St.\"),(\"Rutrum Corp.\",\"P.O. Box 823, 5261 Vehicula Avenue\"),(\"Elit Aliquam Auctor Corp.\",\"Ap #443-5879 Donec Rd.\"),(\"Donec Dignissim Institute\",\"Ap #120-707 Ipsum. St.\"),(\"Lacinia Mattis Integer Ltd\",\"Ap #328-3085 Dui. Av.\"),(\"Lacus Aliquam LLP\",\"Ap #494-5521 Cras Ave\"),(\"Convallis Ligula Donec Incorporated\",\"P.O. Box 369, 7634 Pellentesque Street\"),(\"Mauris Magna Institute\",\"9607 Nulla. Rd.\"),(\"Non Dapibus Rutrum Inc.\",\"3800 Ut, Ave\"),(\"A Enim Suspendisse Incorporated\",\"Ap #462-9995 Eu Street\"),(\"Arcu Nunc LLC\",\"940 Integer Ave\"),(\"Lobortis Corporation\",\"316 Metus Av.\"),(\"Eget Corp.\",\"128-8019 Sem Rd.\"),(\"Urna Nunc Quis LLC\",\"403-6916 Sed St.\"),(\"Elit LLP\",\"Ap #480-880 Nulla. Rd.\"),(\"Ac Sem Ut Incorporated\",\"Ap #677-6819 Enim. Rd.\"),(\"In Consulting\",\"Ap #927-2391 Et St.\"),(\"Donec Consectetuer PC\",\"P.O. Box 398, 6878 Adipiscing Road\"),(\"Donec Dignissim Magna Corporation\",\"590-9561 Pharetra. St.\"),(\"Eleifend Incorporated\",\"1901 Tellus. Avenue\"),(\"Nec Mauris Blandit Industries\",\"870-2690 Malesuada Rd.\"),(\"Tincidunt Aliquam Limited\",\"1653 Pretium Rd.\");")
        op.close()
    }

}