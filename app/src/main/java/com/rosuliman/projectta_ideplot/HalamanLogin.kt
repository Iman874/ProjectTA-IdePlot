package com.rosuliman.projectta_ideplot

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rosuliman.projectta_ideplot.database.ApiResponse
import com.rosuliman.projectta_ideplot.database.RetrofitClient
import com.rosuliman.projectta_ideplot.database.tabel.User
import com.rosuliman.projectta_ideplot.databinding.ActivityHalamanLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HalamanLogin : AppCompatActivity() {

    // Deklarasi binding
    private lateinit var binding: ActivityHalamanLoginBinding

    // SharedPreferences untuk menyimpan data pengguna
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi binding
        binding = ActivityHalamanLoginBinding.inflate(layoutInflater)

        // Set content view dengan root dari binding
        setContentView(binding.root)

        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        // Panggil fungsi untuk navigasi ke halaman daftar
        setupDaftarText()

        // Fungsi untuk login
        binding.btnLogin.setOnClickListener {
            // Menampilkan progressBarLogin dan layer redup saat proses dimulai
            binding.progressBarLogin.visibility = View.VISIBLE
            binding.main.alpha = 0.5F

            val email = binding.formEmailLogin.text.toString()
            val password = binding.formPasswordLogin.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Email atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
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

        // Set click listener untuk daftarText
        binding.daftarText.setOnClickListener {
            // Beralih ke halaman daftar
            navigateToDaftarPage()
        }
    }

    // Fungsi untuk berpindah ke halaman daftar
    private fun navigateToDaftarPage() {
        val intent = Intent(this, HalamanDaftar::class.java)
        startActivity(intent)
        finish()
    }

    // Fungsi untuk login user
    private fun loginUser(email: String, password: String) {
        val nama = ""
        // Membuat objek User
        val user = User(
            nama = nama,
            email = email,
            password = password
        )

        // Menggunakan Retrofit untuk memanggil API
        val call = RetrofitClient.apiService.login(user)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                // Mengambil pesan server
                val pesanServer = response.body()?.pesanServer
                if (response.isSuccessful && response.body() != null) {
                    // Respone jika berhasil login
                    Toast.makeText(this@HalamanLogin, pesanServer, Toast.LENGTH_SHORT).show()

                    // Simpan id user ke SharedPreferences
                    val userId = response.body()?.id
                    if (userId != null) {
                        saveUserId(userId)
                    }

                    // Mengembalikan progress bar dan main view ke posisi semula
                    binding.progressBarLogin.visibility = View.GONE
                    binding.main.alpha = 1F

                    // Redirect ke halaman utama
                    val intent = Intent(this@HalamanLogin, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Tutup halaman login
                } else {
                    // Mengembalikan progress bar dan main view ke posisi semula
                    binding.progressBarLogin.visibility = View.GONE
                    binding.main.alpha = 1F

                    // Tampilkan pesan error dari server
                    Toast.makeText(this@HalamanLogin, "Login Failed: $pesanServer", Toast.LENGTH_SHORT).show()
                    Log.e("Login Failed", "Login failed: $pesanServer")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                binding.progressBarLogin.visibility = View.GONE
                binding.main.alpha = 1F
                Toast.makeText(this@HalamanLogin, "Login Failed: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Login Error", "Login failed: ${t.message}", t)
            }
        })
    }

    // Fungsi untuk menyimpan userId ke SharedPreferences
    private fun saveUserId(userId: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("USER_ID", userId)
        editor.apply()
    }
}
