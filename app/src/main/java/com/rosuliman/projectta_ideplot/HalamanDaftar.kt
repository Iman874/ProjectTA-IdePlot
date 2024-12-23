package com.rosuliman.projectta_ideplot

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.rosuliman.projectta_ideplot.databinding.ActivityHalamanDaftarBinding
import org.json.JSONException
import org.json.JSONObject

class HalamanDaftar : AppCompatActivity() {

    // Deklarasi binding
    private lateinit var binding: ActivityHalamanDaftarBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHalamanDaftarBinding.inflate(layoutInflater)

        val nama = binding.formNamaDaftar.toString()
        val email = binding.formEmailDaftar.toString()
        val password = binding.formPassword1Daftar.toString()



        // Set content view dengan root dari binding
        setContentView(binding.root)

        // Panggil fungsi untuk mengatur listener pada loginText
        setupLoginText()

        binding.btnDaftar.setOnClickListener(){
            registerUser(email, nama, password)
        }

    }

    // Fungsi untuk menangani perubahan warna dan interaksi pada loginText
    @SuppressLint("ClickableViewAccessibility")
    private fun setupLoginText() {
        // Set touch listener untuk loginText
        binding.loginText.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Ubah warna saat ditekan
                    binding.loginText.setTextColor(Color.parseColor("#6B6143"))
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // Ubah warna kembali saat dilepaskan atau aksi dibatalkan
                    binding.loginText.setTextColor(Color.parseColor("#5271FF")) // Warna default (biru)
                }
            }
            false // Menandakan bahwa kita menangani event
        }

        // Set click listener untuk loginText
        binding.loginText.setOnClickListener {
            // Beralih ke HalamanLogin menggunakan Intent
            navigateToLoginPage()
        }
    }

    // Fungsi untuk berpindah ke halaman login
    private fun navigateToLoginPage() {
        val intent = Intent(this, HalamanLogin::class.java)
        startActivity(intent)
    }

    private fun registerUser(email: String, name: String, password: String) {
        val url = "http://192.168.68.71:3000/register" // Ganti dengan IP server jika perlu

        val jsonObject = JSONObject()
        try {
            jsonObject.put("email", email)
            jsonObject.put("name", name)
            jsonObject.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        // Membuat request API
        val request = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                Toast.makeText(this@HalamanDaftar, "Registration Successful", Toast.LENGTH_SHORT).show()
                // Redirect ke halaman login atau beranda setelah registrasi berhasil
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            },
            { error ->
                Toast.makeText(this@HalamanDaftar, "Registration Failed", Toast.LENGTH_SHORT).show()

                // Log error secara rinci untuk membantu debugging
                Log.e("HalamanDaftar", "Registration failed: ${error.message}", error)

            })

        // Menambahkan request ke queue
        val queue = Volley.newRequestQueue(this)
        queue.add(request)
    }
}
