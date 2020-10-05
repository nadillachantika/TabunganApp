package com.nadillla.tabunganapp.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tabungan::class,User::class],version = 1)
abstract class TabunganDb :RoomDatabase(){

            abstract fun tabunganDao (): TabunganDAO
            abstract fun userDao(): UserDAO

            companion object{

                private var INSTANCE : TabunganDb? = null
                fun getInstance(context: Context):TabunganDb {
                    synchronized(this) {
                        var instance: TabunganDb? = INSTANCE
                        if (instance == null) {
                            instance = Room.databaseBuilder(
                                context.applicationContext,
                                TabunganDb::class.java,
                                "tabunganDb.db"
                            ).build()
                        }

                        return instance
                    }
                }
            }

        }
