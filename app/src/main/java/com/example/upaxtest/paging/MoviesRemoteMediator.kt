package com.example.upaxtest.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.upaxtest.webservices.ApiRetrofit
import com.example.upaxtest.db.MoviesDao
import com.example.upaxtest.db.MoviesRemoteKey
import com.example.upaxtest.model.MovieModel
import com.example.upaxtest.utils.Constants
import java.io.InvalidObjectException

@ExperimentalPagingApi
class MoviesRemoteMediator(
    private val moviesDao: MoviesDao,
    private val initialPage: Int = 1
) : RemoteMediator<Int, MovieModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieModel>
    ): MediatorResult {

        return try {

            // Judging the page number
            val page = when (loadType) {
                LoadType.APPEND -> {

                    val remoteKey =
                        getLastRemoteKey(state) ?: throw InvalidObjectException("Invalid")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = true)

                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKeys(state)
                    remoteKey?.next?.minus(1) ?: initialPage
                }
            }

            val response = ApiRetrofit.movieService.getMovies(
                Constants.API_KEY,
                page,
                Constants.apiLanguageEn
            )
            val endOfPagination = response.body()?.results?.size!! < state.config.pageSize

            if (response.isSuccessful) {

                response.body()?.let {

                    // flush our data
                    if (loadType == LoadType.REFRESH) {
                        moviesDao.deleteAllMovies()
                        moviesDao.deleteAllRemoteKeys()
                    }

                    val prev = if (page == initialPage) null else page - 1
                    val next = if (endOfPagination) null else page + 1

                    val list = response.body()?.results?.map {
                        MoviesRemoteKey(it.id, prev, next)
                    }

                    // make list of remote keys

                    if (list != null) {
                        moviesDao.insertAllRemoteKeys(list)
                    }

                    // insert to the room
                    moviesDao.insertMovies(it.results)


                }
                MediatorResult.Success(endOfPagination)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }


        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getClosestRemoteKeys(state: PagingState<Int, MovieModel>): MoviesRemoteKey? {

        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let {
                moviesDao.getAllREmoteKey(it.title)
            }
        }

    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MovieModel>): MoviesRemoteKey? {
        return state.lastItemOrNull()?.let {
            moviesDao.getAllREmoteKey(it.title)
        }
    }

}