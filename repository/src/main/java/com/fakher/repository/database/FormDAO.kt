package com.fakher.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fakher.repository.model.FormPersistence
import io.reactivex.Single

@Dao
interface FormDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForm(form: FormPersistence): Single<Long>

    @Query("SELECT * from FORM ORDER BY hits")
    fun getAllForms(): Single<List<FormPersistence>>

    @Query(
        "SELECT * from FORM WHERE int1 = :int1 AND int2=:int2 AND str1=:str1 AND str2=:str2"
    )
    fun getFormById(
        int1: Int,
        int2: Int,
        str1: String,
        str2: String,
    ): Single<FormPersistence>
}