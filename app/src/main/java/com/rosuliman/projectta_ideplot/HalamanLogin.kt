package com.rosuliman.projectta_ideplot

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.rosuliman.projectta_ideplot.databinding.ActivityHalamanLoginBinding

class HalamanLogin : AppCompatActivity() {

    // Deklarasi binding
    private lateinit var binding: ActivityHalamanLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityHalamanLoginBinding.inflate(layoutInflater)

        // Set content view dengan root dari binding
        setContentView(binding.root)

        // Panggil Fungsi
        setupDaftarText()

        // Fungsi untuk navigasi
        navigateToHalamanUtama()

    }

    private fun navigateToHalamanUtama() {
        binding.btnDaftar.setOnClickListener(){
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }

    // Fungsi untuk menangani perubahan warna dan interaksi pada loginText
    @SuppressLint("ClickableViewAccessibility")
    private fun setupDaftarText() {
        // Set touch listener untuk loginText
        binding.daftarText.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Ubah warna saat ditekan
                    binding.daftarText.setTextColor(Color.parseColor("#6B6143"))
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // Ubah warna kembali saat dilepaskan atau aksi dibatalkan
                    binding.daftarText.setTextColor(Color.parseColor("#5271FF")) // Warna default (biru)
                }
            }
            false // Menandakan bahwa kita menangani event
        }

        // Set click listener untuk loginText
        binding.daftarText.setOnClickListener {
            // Beralih ke HalamanLogin menggunakan Intent
            navigateToDaftarPage()
        }
    }

    // Fungsi untuk berpindah ke halaman login
    private fun navigateToDaftarPage() {
        val intent = Intent(this, HalamanDaftar::class.java)
        startActivity(intent)
    }
}
