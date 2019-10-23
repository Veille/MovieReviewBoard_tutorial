package com.example.MovieReviewBoard.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.MovieReviewBoard.director.Director
import com.example.MovieReviewBoard.director.DirectorRepository
import com.example.MovieReviewBoard.movie.Movie
import com.example.MovieReviewBoard.movie.MovieRepository
import org.springframework.stereotype.Component

@Component
class Query(val movieRepository: MovieRepository, val directorRepository: DirectorRepository): GraphQLQueryResolver {

    fun findAllMovies(): Iterable<Movie> {
        return movieRepository.findAll()
    }

    fun findAllDirectors(): Iterable<Director> {
        return directorRepository.findAll()
    }

    fun countMovies(): Int {
        return movieRepository.count() as Int
    }

    fun countDirectors(): Int {
        return directorRepository.count() as Int
    }

    fun findMoviesByDirector(directorId: Int): Iterable<Movie> {
        val director = directorRepository.findById(directorId)

        return movieRepository.findByDirector(director.get())
    }

    fun countMoviesByDirector(directorId: Int): Int {
        val director = directorRepository.findById(directorId)

        return movieRepository.countByDirector(director.get())
    }
}