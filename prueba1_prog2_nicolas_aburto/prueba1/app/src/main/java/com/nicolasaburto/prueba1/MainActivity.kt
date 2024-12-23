package com.nicolasaburto.prueba1
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nicolasaburto.prueba1.modelo.Menu
import com.nicolasaburto.prueba1.modelo.Mesa
import com.nicolasaburto.prueba1.modelo.Cuenta

class MainActivity : AppCompatActivity() {
    var NumPastel: EditText? = null
    var NumCazuela: EditText? = null
    var tvPastel: TextView? = null
    var tvCazuela: TextView? = null
    var tvComida: TextView? = null
    var tvPropina: TextView? = null
    var tvTotal: TextView? = null
    private lateinit var pedidoPastel: Mesa
    private lateinit var pedidoCazuela: Mesa
    private val pastelDeChoclo = Menu("Pastel de Choclo", 12000)
    private val cazuela = Menu("Cazuela", 10000)
    private val cuentaMesa = Cuenta(1)
    var switchPropina: Switch? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        pedidoPastel = Mesa(pastelDeChoclo, 0)
        pedidoCazuela = Mesa(cazuela, 0)
        NumPastel = findViewById<EditText>(R.id.NumPastel)
        NumCazuela = findViewById<EditText>(R.id.NumCazuela)
        tvPastel = findViewById<TextView>(R.id.tvPastel)
        tvCazuela = findViewById<TextView>(R.id.tvCazuela)
        tvComida = findViewById<TextView>(R.id.tvComida)
        tvPropina = findViewById<TextView>(R.id.tvPropina)
        tvTotal = findViewById<TextView>(R.id.tvTotal)
        switchPropina = findViewById<Switch>(R.id.switch2)
        setupListeners()
    }

    private fun setupListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                actualizarMontos()
            }
        }
        NumPastel?.addTextChangedListener(textWatcher)
        NumCazuela?.addTextChangedListener(textWatcher)
        switchPropina?.setOnCheckedChangeListener { _, isChecked ->
            cuentaMesa.aceptaPropina = isChecked
            actualizarMontos()
        }
    }

    private fun actualizarMontos() {
        //Calcular Subtotales
        pedidoPastel.cantidad = NumPastel?.text.toString().toIntOrNull() ?: 0
        pedidoCazuela.cantidad = NumCazuela?.text.toString().toIntOrNull() ?: 0
        val subtotalPastel = pedidoPastel.calcularSubtotal()
        val subtotalCazuela = pedidoCazuela.calcularSubtotal()
        tvPastel?.text = "$${subtotalPastel}"
        tvCazuela?.text = "$${subtotalCazuela}"

        //Calcular Totales
        cuentaMesa.agregarItem(pastelDeChoclo, pedidoPastel.cantidad)
        cuentaMesa.agregarItem(cazuela, pedidoCazuela.cantidad)
        val totalSinPropina = cuentaMesa.calcularTotalSinPropina()
        val propina = cuentaMesa.calcularPropina()
        val totalConPropina = cuentaMesa.calcularTotalConPropina()
        tvComida?.text = "$${totalSinPropina}"
        tvPropina?.text = "$${if (cuentaMesa.aceptaPropina) propina else 0}"
        tvTotal?.text = "$${totalConPropina}"





        //METODO ALFA
        // Obtener cantidades de cada platillo
        //val cantidadPastel = NumPastel?.text.toString().toIntOrNull() ?: 0
        //val cantidadCazuela = NumCazuela?.text.toString().toIntOrNull() ?: 0

        // Calcular subtotales
        //val subtotalPastel = pastelDeChoclo.precio * cantidadPastel
        //val subtotalCazuela = cazuela.precio * cantidadCazuela

        // Mostrar subtotales
        //tvPastel?.text = "$${subtotalPastel}"
        //tvCazuela?.text = "$${subtotalCazuela}"

        //val totalSinPropina = subtotalPastel + subtotalCazuela
        //val propina =  totalSinPropina * 0.1
        //val totalConPropina = totalSinPropina + propina

        // Actualizar cuenta
        //cuentaMesa.agregarItem(pastelDeChoclo, cantidadPastel)
        //cuentaMesa.agregarItem(cazuela, cantidadCazuela)

        // Calcular totales
        //val totalSinPropina = cuentaMesa.calcularTotalSinPropina()
        //val propina = cuentaMesa.calcularPropina()
        //val totalConPropina = cuentaMesa.calcularTotalConPropina()

        // Mostrar totales
        //tvComida?.text = "$${totalSinPropina}"
        //tvPropina?.text = "$${propina}"
        //tvTotal?.text = "$${totalConPropina}"
    }




}



