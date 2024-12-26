package com.rosuliman.projectta_ideplot.database.tabel

data class Lokasi(
    val created_by: Int,
    val gambar_lokasi: String,
    val tipe_lokasi: String,
    val nama_lokasi: String,
    val deskripsi_lokasi: String,
    val alamat_lokasi: String
)
