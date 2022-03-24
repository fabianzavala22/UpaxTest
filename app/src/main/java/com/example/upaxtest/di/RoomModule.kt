package com.example.upaxtest.di

import android.content.Context
import com.example.upaxtest.db.MoviesDao
import com.example.upaxtest.db.MoviesDataBase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideMovieDao(context: Context): MoviesDao{

        return MoviesDataBase.getInstance(context).moviesDAO
    }
}

@Module(subcomponents = [TestComponent::class])
class SubcomponentsModule {}