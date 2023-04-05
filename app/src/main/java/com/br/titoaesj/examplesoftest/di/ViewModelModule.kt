package com.br.titoaesj.examplesoftest.di

import com.br.titoaesj.examplesoftest.data.repository.JediRepository
import com.br.titoaesj.examplesoftest.data.api.StarWarsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideStarWarApi(retrofit: Retrofit): StarWarsApi =
        retrofit.create(StarWarsApi::class.java)

    @Provides
    @ViewModelScoped
    fun provideMainRepository(starWarsApi: StarWarsApi): JediRepository =
        JediRepository(starWarsApi = starWarsApi)

}