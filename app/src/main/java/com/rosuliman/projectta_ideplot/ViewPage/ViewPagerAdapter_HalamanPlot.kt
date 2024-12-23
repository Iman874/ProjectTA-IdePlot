package com.rosuliman.projectta_ideplot.ViewPage

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rosuliman.projectta_ideplot.content.halaman_plot.HalamanPengaturanBab
import com.rosuliman.projectta_ideplot.content.halaman_plot.HalamanPengaturanKarakter
import com.rosuliman.projectta_ideplot.content.halaman_plot.HalamanPengaturanPlot
import com.rosuliman.projectta_ideplot.content.halaman_plot.HalamanPengaturanTimeline

class ViewPagerAdapter_HalamanPlot(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        HalamanPengaturanBab(),
        HalamanPengaturanPlot(),
        HalamanPengaturanKarakter(),
        HalamanPengaturanTimeline
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position] as Fragment
    }
}

