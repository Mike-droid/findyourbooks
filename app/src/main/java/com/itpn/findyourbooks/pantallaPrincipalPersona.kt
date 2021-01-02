package com.itpn.findyourbooks

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        //insertarLibros()
        // SOLO EJECUTAR ESAS FUNCIONES LA PRIMERA VEZ QUE SE CORRE LA APP
        //******************************************************************//

    }

    fun buscar(v:View){
        rbLibro = findViewById(R.id.rbLibro)
        rbAutor = findViewById(R.id.rbAutor)
        rbTema = findViewById(R.id.rbTema)
        tvResultado = findViewById(R.id.tvResultado)
        op = c.readableDatabase
        etBusqueda = findViewById(R.id.etBusqueda)
        var cursor:Cursor
        var resultado = ""

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

}