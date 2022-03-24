package com.example.upaxtest.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.upaxtest.paging.MoviePagingSource
import javax.inject.Inject

class MoviesViewModel @Inject constructor() : ViewModel() {

    val movieslist = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource()
    }.flow.cachedIn(viewModelScope)

   /* Función pendiente de implementar para guardar los datos en Room de la paginación mediante lq
   clase MoviesRemoteMerdiator*/

    /* @OptIn(ExperimentalPagingApi::class)
        val page = Pager(
            PagingConfig(pageSize = 30),
            remoteMediator = MoviesRemoteMediator(moviesDao, 1)
        ) {
            moviesDao.getAllMovies()
        }.flow
    }*/
}