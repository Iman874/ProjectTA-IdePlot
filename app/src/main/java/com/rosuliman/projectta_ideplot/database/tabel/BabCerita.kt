package com.rosuliman.projectta_ideplot.database.tabel

data class BabCerita(
    val id_plot: Int,
    val created_by: Int,
    val judul_bab: String,
    val ringkasan_bab: String,
    val tanggal_bab: String // Gunakan String atau Date sesuai kebutuhan
)
