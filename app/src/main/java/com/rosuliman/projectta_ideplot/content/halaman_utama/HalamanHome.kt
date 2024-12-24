package com.rosuliman.projectta_ideplot.content.halaman_utama

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rosuliman.projectta_ideplot.content.halaman_tambah_edit.HalamanIde
import com.rosuliman.projectta_ideplot.databinding.FragmentHalamanHomeBinding

class HalamanHome : Fragment() {
    private var _binding: FragmentHalamanHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHalamanHomeBinding.inflate(inflater, container, false)

        // akses binding ke elemen pada halam home
        binding.btnTextBuatIde.setOnClickListener {
            navigateToHalamanIde()
        }
        binding.btnBuatIde.setOnClickListener {
            navigateToHalamanIde()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // navigate to halaman ide
    private fun navigateToHalamanIde(){
        // Pindah ke HalamanIde menggunakan Intent
        val intent = Intent(requireContext(), HalamanIde::class.java)
        startActivity(intent)
    }

}