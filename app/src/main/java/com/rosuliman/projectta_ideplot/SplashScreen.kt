package com.rosuliman.projectta_ideplot

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Dapatkan ImageView untuk logo
        val logo = findViewById<ImageView>(R.id.logo_app)

        // Membuat animasi zoom-in untuk logo
        val zoomIn = ScaleAnimation(
            0f, 1f,  // Mulai dari ukuran 0 (invisible) hingga 1 (normal)
            0f, 1f,  // Sama untuk sumbu Y (vertikal)
            Animation.RELATIVE_TO_SELF, 0.5f,  // Zoom di tengah
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        zoomIn.duration = 1200 // Durasi animasi dalam milidetik (1 detik)
        zoomIn.startOffset = 500 // Mulai animasi setelah 0.5 detik
        logo.startAnimation(zoomIn) // Jalankan animasi
        logo.visibility = ImageView.VISIBLE // Tampilkan logo setelah animasi dimulai

        // Dapatkan ImageView untuk slogan
        val slogan = findViewById<ImageView>(R.id.logo_app_slogan)

        // Membuat animasi translate untuk logo
        val slideUp = TranslateAnimation(0f, 0f, 1000f, 0f) // Logo bergerak dari bawah (1000f) ke atas (0f)
        slideUp.duration = 1700 // Durasi animasi dalam milidetik
        slideUp.fillAfter = true // Agar posisi akhir tetap berada di atas setelah animasi selesai
        slogan.startAnimation(slideUp) // Jalankan animasi

        // Menunda transisi ke HalamanHome
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HalamanDaftar::class.java))
            finish() // Menghapus SplashScreen dari backstack
        }, 3000) // Durasi 3 detik
    }
}
