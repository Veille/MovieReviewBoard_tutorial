package com.example.MovieReviewBoard.movie

import com.example.MovieReviewBoard.director.Director
import org.springframework.data.repository.CrudRepository

interface MovieRepository : CrudRepository<Movie, Int> {
    fun findByDirector(director: Director): Iterable<Movie>
    fun countByDirector(director: Director): Int
}