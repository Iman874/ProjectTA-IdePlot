package com.rosuliman.projectta_ideplot.ViewPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rosuliman.projectta_ideplot.content.halaman_utama.HalamanHome
import com.rosuliman.projectta_ideplot.content.halaman_utama.HalamanMenuList
import com.rosuliman.projectta_ideplot.content.halaman_utama.HalamanProfile

class ViewPagerAdapter_HalamanUtama(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        HalamanHome(),
        HalamanMenuList(),
        HalamanProfile()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}

