package com.nadillla.tabunganapp.Local

import androidx.room.*

@Dao
interface TabunganDAO {

    @Query(value = "SELECT * FROM tabungan_data" )
    fun getDataTabungan():List<Tabungan>

    @Query("SELECT SUM(tabungan_jumlah) as jumlah FROM tabungan_data")
    fun getTotal():String?

    @Insert
    fun insertTabungan(tabungan: Tabungan)

    @Update
    fun updateTabungan(tabungan: Tabungan?)

    @Delete
    fun deleteTabungan(tabungan: Tabungan?)



    //    @Insert
//    fun insertTabungan2(tabungan: Tabungan):Long
//
//    @Insert
//    fun  insertTabungan3(tabungan1: Tabungan,tabungan2:Tabungan,tabungan3: Tabungan):List<Long>
//
//    @Insert
//    fun insertTabungan4(tabungan4: List<Tabungan>):List<Long>
//
//    @Insert
//    fun insertTabungan5(tabungan: Tabungan, tabungans: List<Tabungan>):List<Long>
//
//


}