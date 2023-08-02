package com.YYKS.InfoPendakianYYKS.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.YYKS.InfoPendakianYYKS.fragment.FragmentPeralatan
import com.YYKS.InfoPendakianYYKS.fragment.FragmentTips



class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    // Ketika parameter bernilai 0 maka akan dikembalikan ke fragmentPeralatan jika 1 ke FragmentTips
    override fun getItem(position: Int): Fragment {
        lateinit var fragment: Fragment
        when (position) {
            0 -> fragment = FragmentPeralatan()
            1 -> fragment = FragmentTips()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title = ""
        when (position) {
            0 -> title = "Peralatan"
            1 -> title = "Tips"
        }
        return title
    }
}