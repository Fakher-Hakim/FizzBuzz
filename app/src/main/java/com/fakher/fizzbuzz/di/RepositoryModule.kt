package com.fakher.fizzbuzz.di

import android.content.Context
import com.fakher.business.model.FormEntity
import com.fakher.business.repository.FormRepository
import com.fakher.common.Mapper
import com.fakher.repository.database.FormDAO
import com.fakher.repository.database.MainDatabase
import com.fakher.repository.mapper.RepositoryFormMapper
import com.fakher.repository.model.FormPersistence
import com.fakher.repository.repository.FormRepositoryImpl
import com.fakher.repository.source.FormDataSource
import com.fakher.repository.source.local.FormDataSourceLocalImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepositoryFormMapper(): Mapper<FormPersistence, FormEntity> = RepositoryFormMapper()

    @Provides
    @Singleton
    fun providesMainDatabase(context: Context): MainDatabase =
        MainDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideFormDao(db: MainDatabase): FormDAO {
        return db.getFormDao()
    }

    @Provides
    @Singleton
    fun providesFormLocalDataSource(formDAO: FormDAO): FormDataSource =
        FormDataSourceLocalImpl(formDAO)

    @Provides
    @Singleton
    fun providesFormRepository(
        formDataSource: FormDataSource,
        mapper: Mapper<FormPersistence, FormEntity>
    ): FormRepository =
        FormRepositoryImpl(formDataSource, mapper)
}