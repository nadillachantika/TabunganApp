package com.nadillla.tabunganapp.Local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDAO {


    @Query( "SELECT * FROM user_data WHERE user_email=:email AND user_password=:password LIMIT 1")
    fun login(email: String?,password:String?):User


    @Query("SELECT * FROM user_data WHERE user_email=:email LIMIT 1")
    fun getUser(email: String?):User


    @Insert
    fun register(user: User)
}
