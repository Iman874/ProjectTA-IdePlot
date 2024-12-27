package com.rosuliman.projectta_ideplot.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.rosuliman.projectta_ideplot.database.tabel.Plot
import com.rosuliman.projectta_ideplot.databinding.ViewPlotAdapterBinding

class PlotAdapter(
    private val context: Context,
    private val plotList: List<Plot>
) : BaseAdapter() {

    override fun getCount(): Int {
        return plotList.size
    }

    override fun getItem(position: Int): Any {
        return plotList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Inisialisasi ViewBinding
        val binding = ViewPlotAdapterBinding.inflate(LayoutInflater.from(context), parent, false)

        // Ambil item dari plotList berdasarkan posisi
        val plot = plotList[position]

        // Set data ke ViewBinding
        binding.textViewTitle.text = plot.judul_plot
        binding.textViewDescription.text = plot.deskripsi_plot

        return binding.root
    }
}
