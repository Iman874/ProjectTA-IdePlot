package com.rosuliman.projectta_ideplot.content.halaman_utama

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rosuliman.projectta_ideplot.adapter.PlotAdapter
import com.rosuliman.projectta_ideplot.database.ApiResponseList
import com.rosuliman.projectta_ideplot.database.RetrofitClient
import com.rosuliman.projectta_ideplot.database.tabel.Plot
import com.rosuliman.projectta_ideplot.databinding.FragmentHalamanMenuListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HalamanMenuList : Fragment() {

    private var _binding: FragmentHalamanMenuListBinding? = null
    private val binding get() = _binding!!

    private lateinit var plotAdapter: PlotAdapter
    private val plotList = mutableListOf<Plot>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentHalamanMenuListBinding.inflate(inflater, container, false)

        // Mendapatkan userId dari SharedPreferences
        val userId = getUserId()
        if (userId != -1) {
            fetchPlots(userId)
        } else {
            Toast.makeText(context, "User not logged in", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    // Fungsi untuk mengambil userId dari SharedPreferences
    private fun getUserId(): Int {
        val sharedPreferences = requireActivity().getSharedPreferences("UserPreferences", android.content.Context.MODE_PRIVATE)
        return sharedPreferences.getInt("USER_ID", -1) // Mengembalikan -1 jika tidak ditemukan
    }

    // Fungsi untuk mengambil data plot dari API menggunakan Retrofit
    private fun fetchPlots(userId: Int) {
        Log.d("HalamanMenuList", "Fetching plots for userId: $userId")
        RetrofitClient.apiService.getPlot(userId).enqueue(object : Callback<ApiResponseList> {
            override fun onResponse(call: Call<ApiResponseList>, response: Response<ApiResponseList>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.plot?.isNotEmpty() == true) {
                        Log.d("HalamanMenuList", "Fetched plots: ${apiResponse.plot}")

                        // Update data dan adapter
                        plotList.clear()
                        plotList.addAll(apiResponse.plot)

                        plotAdapter = PlotAdapter(requireContext(), plotList)
                        binding.ListViewPlot.adapter = plotAdapter
                    } else {
                        Log.e("HalamanMenuList", "No plots found or empty response")
                        Toast.makeText(context, "No plots found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("HalamanMenuList", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Failed to load plots", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponseList>, t: Throwable) {
                Log.e("HalamanMenuList", "Error fetching plots: ${t.message}", t)
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Menghindari memory leaks
    }
}
