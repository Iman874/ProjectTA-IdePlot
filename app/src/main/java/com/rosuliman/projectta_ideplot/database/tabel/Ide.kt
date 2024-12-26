package com.rosuliman.projectta_ideplot.database.tabel

data class Ide(
    val tipe_ide: String,
    val created_by: Int,
    val judul_ide: String,
    val deskripsi: String,
    val tanggal_ide: String // Gunakan String atau Date sesuai kebutuhan
)
