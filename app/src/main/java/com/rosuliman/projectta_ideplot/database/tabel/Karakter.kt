package com.rosuliman.projectta_ideplot.database.tabel

data class Karakter(
    val created_by: Int,
    val gambar_karakter: String,
    val nama: String,
    val jenis_kelamin: String,
    val tanggal_lahir: String, // Gunakan String atau Date sesuai kebutuhan
    val deskripsi_karakter: String,
    val motivasi: String,
    val kepribadian: String
)
