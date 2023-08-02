package com.YYKS.InfoPendakianYYKS.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.YYKS.InfoPendakianYYKS.R
import com.YYKS.InfoPendakianYYKS.adapter.PeralatanAdapter
import com.YYKS.InfoPendakianYYKS.model.ModelPeralatan
import kotlinx.android.synthetic.main.fragment_peralatan.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

class FragmentPeralatan : Fragment() {

    var modelPeralatan: MutableList<ModelPeralatan> = ArrayList()
    lateinit var peralatanAdapter: PeralatanAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_peralatan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        peralatanAdapter = PeralatanAdapter(context, modelPeralatan)
        rvPeralatan.setLayoutManager(LinearLayoutManager(context))
        rvPeralatan.setAdapter(peralatanAdapter)
        rvPeralatan.setHasFixedSize(true)

        //get data nama gunung
        getPeralatanGunung()
    }

    // Fungsi untuk membaca data dari file JSON
    private fun getPeralatanGunung() {
        try {
            val stream = requireContext().assets.open("peralatan.json") // membuka file json yg ada di folder assets
            val size = stream.available() // mendapatkan ukuran byte dari file yang dibuka
            val buffer = ByteArray(size) // penyimpanan tempat sementara dengan ukuran sesuai file json
            stream.read(buffer) // membaca seluruh isi file json dan menyimpannya di buffer
            stream.close()
            val strContent = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObject = JSONObject(strContent)
                val jsonArray = jsonObject.getJSONArray("peralatan")
                for (i in 0 until jsonArray.length()) {
                    val jsonObjectData = jsonArray.getJSONObject(i)
                    val dataApi = ModelPeralatan() // Membuat sebuah objek dari kelas ModelPeralatan untuk menyimpan data dari objek JSON yang saat ini sedang diproses.
                    dataApi.strNamaPeralatan = jsonObjectData.getString("nama")
                    dataApi.strImagePeralatan = jsonObjectData.getString("image_url")
                    dataApi.strTipePeralatan = jsonObjectData.getString("tipe")
                    dataApi.strDeskripsiPeralatan = jsonObjectData.getString("deskripsi")
                    dataApi.strTipsPeralatan = jsonObjectData.getString("tips")
                    modelPeralatan.add(dataApi)
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } catch (ignored: IOException) {
            Toast.makeText(context, "Oops, ada yang tidak beres. Coba ulangi beberapa saat lagi.", Toast.LENGTH_SHORT).show()
        }
    }

}