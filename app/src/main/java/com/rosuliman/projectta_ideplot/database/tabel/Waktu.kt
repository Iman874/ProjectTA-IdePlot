package com.rosuliman.projectta_ideplot.database.tabel

data class Waktu(
    val created_by: Int,
    val nama_peristiwa: String,
    val ringkasan_peristiwa: String,
    val awal_waktu_peristiwa: String, // Gunakan String atau Date sesuai kebutuhan
    val akhir_waktu_peristiwa: String // Gunakan String atau Date sesuai kebutuhan
)
