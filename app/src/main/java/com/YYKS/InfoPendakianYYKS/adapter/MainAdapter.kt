package com.YYKS.InfoPendakianYYKS.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.YYKS.InfoPendakianYYKS.R
import com.YYKS.InfoPendakianYYKS.activities.ListGunungActivity
import com.YYKS.InfoPendakianYYKS.model.ModelMain
import kotlinx.android.synthetic.main.list_item_main.view.*


class MainAdapter(private val context: Context?, private val modelMain:
List<ModelMain>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var imageLokasiDrawable = 0

// implementasi untuk membuat objek ViewHolder baru untuk menampilkan item gunung dalam RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_main, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelMain[position]

        holder.tvLokasi.text = data.strLokasi

        if (data.strLokasi == "Jawa Timur")
            imageLokasiDrawable = R.drawable.ic_jatim
        else if (data.strLokasi == "Jawa Tengah")
            imageLokasiDrawable = R.drawable.ic_jateng
        else if (data.strLokasi == "Jawa Barat")
            imageLokasiDrawable = R.drawable.ic_jabar
        else if (data.strLokasi == "Luar Pulau Jawa")
            imageLokasiDrawable = R.drawable.ic_luar_jawa

        holder.imageLokasi.setImageResource(imageLokasiDrawable)

        holder.cvListLokasi.setOnClickListener {
            val intent = Intent(context, ListGunungActivity::class.java)
            intent.putExtra(ListGunungActivity.LIST_GUNUNG, modelMain[position])
            context?.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modelMain.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cvListLokasi: CardView
        var tvLokasi: TextView
        var imageLokasi: ImageView

        init {
            cvListLokasi = itemView.cvListLokasi
            tvLokasi = itemView.tvLokasi
            imageLokasi = itemView.imageLokasi
        }
    }

}