package com.YYKS.InfoPendakianYYKS.activities

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.YYKS.InfoPendakianYYKS.R
import com.YYKS.InfoPendakianYYKS.model.ModelPeralatan
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_peralatan.*

class DetailTipsActivity : AppCompatActivity() {

    lateinit var modelPeralatan: ModelPeralatan
    var strNamaAlat: String? = null
    var strTips: String? = null

    // Memanggil tampilan dari activitydetailperlatan
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_peralatan)

        //set transparent statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        setSupportActionBar(toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //get data intent
        modelPeralatan = intent.getSerializableExtra(DETAIL_PERALATAN) as ModelPeralatan
        if (modelPeralatan != null) {
            strNamaAlat = modelPeralatan.strNamaPeralatan
            strTips = modelPeralatan.strTipsPeralatan

            Glide.with(this)
                    .load(modelPeralatan.strImagePeralatan)
                    .into(imageAlat)

            tvNamaAlat.setText(strNamaAlat)
            tvDetailAlat.setText(strTips)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val DETAIL_PERALATAN = "DETAIL_PERALATAN"
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}