package com.nadillla.tabunganapp.Local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabungan_data")
data class Tabungan (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="tabungan_id")
    val id : Int? = null,

    @ColumnInfo(name = "tabungan_tgl")
    val tgl : String?,

    @ColumnInfo(name = "tabungan_jumlah")
    val jumlah : Int?,

    @ColumnInfo(name = "tabugan_keterangan")
    val keterangan : String?



)