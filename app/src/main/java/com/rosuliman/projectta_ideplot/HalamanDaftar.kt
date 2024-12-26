@file:Suppress("DEPRECATION")

package com.rosuliman.projectta_ideplot

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rosuliman.projectta_ideplot.database.ApiRespone
import com.rosuliman.projectta_ideplot.database.RetrofitClient
import com.rosuliman.projectta_ideplot.database.tabel.User
import com.rosuliman.projectta_ideplot.databinding.ActivityHalamanDaftarBinding
import retrofit2.Call

class HalamanDaftar : AppCompatActivity() {

    // Deklarasi binding
    private lateinit var binding: ActivityHalamanDaftarBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHalamanDaftarBinding.inflate(layoutInflater)

        // Set content view dengan root dari binding
        setContentView(binding.root)

        // Panggil fungsi untuk mengatur listener pada loginText
        setupLoginText()

        binding.btnDaftar.setOnClickListener {
            // Menampilkan progressBarLogin dan layer redup saat proses dimulai
            binding.progressBar.visibility = View.VISIBLE
            binding.main.alpha = 0.5F

            if (isInternetAvailable()) {
                val email = binding.formEmailDaftar.text.toString()
                val nama = binding.formNamaDaftar.text.toString()
                val password = binding.formPassword1Daftar.text.toString()
                val passwordCheck = binding.formPassword2Daftar.text.toString()

                if (passwordCheck != password){
                    Toast.makeText(this, "Konfirmasi Password tidak sama!", Toast.LENGTH_SHORT).show()
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Masukan Format Email yang benar!", Toast.LENGTH_SHORT).show()
                }
                if (email.isNotEmpty() && nama.isNotEmpty() && password.isNotEmpty()) {
                    registerUser(email, nama, password)
                } else {
                    Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tidak dapat terhubung ke jaringan internet", Toast.LENGTH_SHORT).show()
            }
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

    // Fungsi untuk mengecek koneksi internet
    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    // Fungsi untuk login user
    private fun registerUser(email: String, nama: String, password: String) {
        // Membuat objek User
        val user = User(
            nama = nama,
            email = email,
            password = password)

        // Menggunakan Retrofit untuk memanggil API
        val call = RetrofitClient.apiService.register(user)

        call.enqueue(object : retrofit2.Callback<ApiRespone> {
            override fun onResponse(call: Call<ApiRespone>, response: retrofit2.Response<ApiRespone>) {
                // Mengambil pesan server
                val pesanServer = response.body()?.pesanServer
                if (response.isSuccessful) {
                    // Ambil userId jika response berhasil
                    val userId = response.body()?.id
                    if (userId != null) {
                        Toast.makeText(this@HalamanDaftar, "Registrasi Successful", Toast.LENGTH_SHORT).show()
                        Log.e("Registrasi Succes", "Registrasi succes: ${pesanServer}")

                        // Simpan userId ke SharedPreferences
                        saveUserId(userId)

                        // Mengembalikan progress bar dan main view ke posisi semula
                        binding.progressBar.visibility = View.GONE
                        binding.main.alpha = 0F

                        // Redirect ke halaman login atau beranda setelah login berhasil
                        val intent = Intent(this@HalamanDaftar, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Tutup halaman login
                    } else {
                        // Tampilkan pesan error untuk id yang tidak ditemukan
                        Log.e("ID bermasalah", "ID user bermasalah: ${pesanServer}")
                    }
                } else {
                    // Mengembalikan progress bar dan main view ke posisi semula
                    binding.progressBar.visibility = View.GONE
                    binding.main.alpha = 0F

                    // Tampilkan pesan error dari server
                    Toast.makeText(this@HalamanDaftar, "Login Failed: ${pesanServer}", Toast.LENGTH_SHORT).show()
                    Log.e("Registrasi Failed", "Registrasi failed: ${pesanServer}")
                }
            }

            override fun onFailure(call: Call<ApiRespone>, t: Throwable) {
                Toast.makeText(this@HalamanDaftar, "Login Failed: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("Registrasi Error", "Registrasi failed: ${t.message}", t)
            }
        })
    }

    private fun saveUserId(userId: Int) {
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("USER_ID", userId)
        editor.apply()
    }

}
