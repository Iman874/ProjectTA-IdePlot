package com.rosuliman.projectta_ideplot.database

import com.rosuliman.projectta_ideplot.database.tabel.Plot

data class ApiResponseList(
    val pesanServer: String?,  // Pesan server (jika ada)
    val plot: List<Plot>       // List of Plot objects
)
