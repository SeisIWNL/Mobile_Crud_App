package pe.edu.cibertec.cardview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.cibertec.proyectofinalkotlin.R
import pe.edu.cibertec.proyectofinalkotlin.entities.Product

class RecycleViewProductAdapter ( private var productList: List<Product>) : RecyclerView.Adapter<RecycleViewProductAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_list_item, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvProductTitle.text = productList[position].title
        holder.ivProductoImg.setImageResource(productList[position].image)
        holder.tvProductoDescrip.text = productList[position].descrp

        holder.cardView.setOnClickListener{
            val context = holder.itemView.context
            val intent = Intent(context, ProducDetailActivity::class.java).apply {
                putExtra("imagen",productList[position].image)
                putExtra("titulo",productList[position].title)
                putExtra("descripcion",productList[position].descrp)
                putExtra("precio",productList[position].precio)
                putExtra("stock",productList[position].stock)
            }
            context.startActivity(intent)
        }
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvProductTitle : TextView = itemView.findViewById(R.id.tvProductoTitle)
        val ivProductoImg : ImageView = itemView.findViewById(R.id.ivProductImg)
        val tvProductoDescrip: TextView = itemView.findViewById(R.id.tvProductoDescrip)

        val cardView : CardView = itemView.findViewById(R.id.cardView)
    }
    fun updateList(newProducts: List<Product>) {
        productList = newProducts
        notifyDataSetChanged()
    }
}