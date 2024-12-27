package com.rosuliman.projectta_ideplot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rosuliman.projectta_ideplot.ViewPage.ViewPagerAdapter_HalamanUtama
import com.rosuliman.projectta_ideplot.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Konfigurasi ViewPager2
        setupViewPager()
    }

    /*
    private fun setupNavHostFragment() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        // Menambahkan fragment Header secara dinamis
        if (supportFragmentManager.findFragmentById(R.id.fragment) == null) {
            val headerFragment = HeaderHalamanUtama()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, headerFragment) // R.id.fragment adalah ID dari FragmentContainerView di XML
                .commit()
        }

        // Menghubungkan BottomNavigationView dengan NavController
        binding.navView.setupWithNavController(navController)
    }

     */

    private fun setupViewPager() {
        // ViewPager2
        val viewPager = binding.viewPager
        val adapter = ViewPagerAdapter_HalamanUtama(this)
        viewPager.adapter = adapter

        // BottomNavigationView
        val navView: BottomNavigationView = binding.navView
        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Nav_halamanHome -> viewPager.currentItem = 0
                R.id.Nav_halamanMenuList -> viewPager.currentItem = 1
                R.id.Nav_halamanProfile -> viewPager.currentItem = 2
            }
            true
        }

        // Sinkronisasi perubahan halaman dengan BottomNavigationView
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                navView.menu.getItem(position).isChecked = true
            }
        })
    }
}
