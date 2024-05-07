package com.vedantjha.browsemovies.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vedantjha.browsemovies.data.model.Movie
import com.vedantjha.browsemovies.data.network.MovieApiService
import com.vedantjha.browsemovies.utils.Constant
import java.lang.Exception

class MoviePagingSource(private val apiService: MovieApiService) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val position = params.key ?: 1
            val response = apiService.fetchMovies(
                api_key = Constant.ACCESS_TOKEN,
                language = "en-US",
                page = position
            )
            if (response.isSuccessful && response.body() != null) {
                LoadResult.Page(
                    data = response.body()!!.results,
                    prevKey =  if (position == 1) null else (position - 1),
                    nextKey =  if (position == response.body()!!.total_pages) null else (position + 1)
                )
            } else {
                LoadResult.Error(throw Exception("No Response for position: $position"))
            }


        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

}