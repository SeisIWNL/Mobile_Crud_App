package pe.edu.cibertec.cardview

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import pe.edu.cibertec.proyectofinalkotlin.R

class ProducDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_product_detail_item)

        var imagen = intent.getIntExtra("imagen",0)
        var titulo = intent.getStringExtra("titulo")
        var descripcion = intent.getStringExtra("descripcion")
        var precio = intent.getDoubleExtra("precio",0.0)
        var stock = intent.getIntExtra("stock",0)

        val imagenView: ImageView = findViewById(R.id.imageView)
        val txtTitulo: TextView = findViewById(R.id.txtTitulo)
        val txtDescrip: TextView = findViewById(R.id.txtDescrip)
        val txtPrecio: TextView = findViewById(R.id.txtPrecio)
        val txtStock: TextView = findViewById(R.id.txtStock)
        val btnBack: Button = findViewById(R.id.btnVolver)

        imagenView.setImageResource(imagen)
        txtTitulo.text = titulo
        txtDescrip.text = descripcion
        txtPrecio.text = precio.toString()
        txtStock.text = stock.toString()

        btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}