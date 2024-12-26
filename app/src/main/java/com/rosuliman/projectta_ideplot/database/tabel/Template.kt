package com.rosuliman.projectta_ideplot.database.tabel

data class Template(
    val kode_developer: Int,
    val nama_template: String,
    val pairing_template: String,
    val tanggal_terbit: String, // Gunakan String atau Date sesuai kebutuhan
    val tema_template: String,
    val jenis_template: String
)
