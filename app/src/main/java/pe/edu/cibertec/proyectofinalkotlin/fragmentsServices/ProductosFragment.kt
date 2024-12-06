package pe.edu.cibertec.proyectofinalkotlin.fragmentsServices

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import pe.edu.cibertec.cardview.RecycleViewProductAdapter
import pe.edu.cibertec.proyectofinalkotlin.R
import pe.edu.cibertec.proyectofinalkotlin.databinding.FragmentProductosBinding
import pe.edu.cibertec.proyectofinalkotlin.entities.Product

class ProductosFragment : Fragment(R.layout.fragment_productos) {

    private var recyclerView : RecyclerView? = null
    private var recycleViewProductAdapter : RecycleViewProductAdapter?=null
    private var productList = mutableListOf<Product>()
    private lateinit var filteredProductList: MutableList<Product>
    private var _binding: FragmentProductosBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        _binding = FragmentProductosBinding.inflate(layoutInflater)
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnBuscar = binding.btnBuscar
        val txtFiltro = binding.txtFilter
        productList = ArrayList()
        recyclerView = binding.rvProductList
        recycleViewProductAdapter = RecycleViewProductAdapter(productList)
        val layoutManager : RecyclerView.LayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = recycleViewProductAdapter
        prepareProductListData()

        btnBuscar.setOnClickListener{
            val txtfiltro = txtFiltro.text.toString()
            filteredProductList = productList.filter { product ->
                product.title.contains(txtfiltro, ignoreCase = true)
            }.toMutableList()
            recycleViewProductAdapter!!.updateList(filteredProductList)
        }
    }


    private fun prepareProductListData() {
        var product = Product("Laptop HP", R.drawable.laptop, "Laptop HP con procesador Intel Core i7 y 16GB de RAM.", 1200.00, 5)
        productList.add(product)
        product = Product("Smartphone Samsung", R.drawable.smarphone, "Samsung Galaxy S21 con pantalla AMOLED.", 800.00, 4)
        productList.add(product)
        product = Product("Televisor LG", R.drawable.televisor, "Televisor LG 55 pulgadas 4K UHD con HDR.", 600.00, 4)
        productList.add(product)
        product = Product("Auriculares Sony", R.drawable.audifonos, "Auriculares inalámbricos Sony con cancelación de ruido.", 150.00, 5)
        productList.add(product)
        product = Product("Cámara Canon", R.drawable.camara, "Cámara réflex Canon EOS Rebel con lente 18-55mm.", 700.00, 4)
        productList.add(product)
        product = Product("Impresora Epson", R.drawable.impresora, "Impresora multifuncional Epson con tanque de tinta.", 200.00, 4)
        productList.add(product)
        product = Product("Tablet Apple", R.drawable.tablet, "iPad Pro de 11 pulgadas con 256GB de almacenamiento.", 900.00, 5)
        productList.add(product)
        product = Product("Monitor Dell", R.drawable.monitor, "Monitor Dell 27 pulgadas 4K UHD.", 400.00, 4)
        productList.add(product)
        product = Product("Router TP-Link", R.drawable.router, "Router inalámbrico TP-Link con WiFi 6 y alta velocidad.", 120.00, 4)
        productList.add(product)
        product = Product("Teclado Logitech", R.drawable.teclado, "Teclado mecánico Logitech con retroiluminación RGB.", 100.00, 5)
        productList.add(product)
        product = Product("Mouse Razer", R.drawable.mouse, "Mouse inalámbrico Razer para gaming con alta precisión.", 80.00, 5)
        productList.add(product)
        product = Product("Disco Duro Externo WD", R.drawable.disco, "Disco duro externo WD de 2TB para almacenamiento adicional.", 90.00, 4)
        productList.add(product)
        product = Product("Parlante JBL", R.drawable.parlante, "Parlante portátil JBL con Bluetooth y sonido potente.", 120.00, 5)
        productList.add(product)
        product = Product("Consola PlayStation", R.drawable.consola, "Consola PlayStation 5 con mando inalámbrico.", 500.00, 5)
        productList.add(product)
        product = Product("Silla Gamer", R.drawable.silla, "Silla gamer ergonómica con soporte lumbar.", 250.00, 4)
        productList.add(product)
        product = Product("Smartwatch Apple", R.drawable.relog, "Apple Watch Series 6 con GPS y monitor de salud.", 400.00, 5)
        productList.add(product)
        recycleViewProductAdapter!!.notifyDataSetChanged()
    }
}