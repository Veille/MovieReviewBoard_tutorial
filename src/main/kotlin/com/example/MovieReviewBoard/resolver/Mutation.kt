package com.example.MovieReviewBoard.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.MovieReviewBoard.director.Director
import com.example.MovieReviewBoard.director.DirectorRepository
import com.example.MovieReviewBoard.movie.Movie
import com.example.MovieReviewBoard.movie.MovieRepository
import org.springframework.stereotype.Component

@Component
class Mutation (val directorRepository: DirectorRepository, val movieRepository: MovieRepository): GraphQLMutationResolver {

    fun updateDirector(directorId: Int, director: Director): Director {

        val oldDirector = directorRepository.findById(directorId)

        oldDirector.ifPresent {
            it.firstName = director.firstName
            it.lastName  = director.lastName
        }

        return oldDirector.get()
    }

    fun updateMovieRating(movieId: Int, vote: Int): Int {
        val movie = movieRepository.findById(movieId)

        movie.ifPresent {
            it.rating = it.rating + vote
            movieRepository.save(it)
        }

        return movie.get().rating
    }

    fun newMovie(title: String, directorID: Int, releaseDate: String, rating: Int): Movie {
        val director = directorRepository.findById(directorID)
        val movie = Movie(title, director.get(), rating, releaseDate)

        return movieRepository.save(movie)
    }

    fun newDirector(directorId: Int, firstName: String, lastName: String): Director {
        val director = Director(firstName, lastName, directorId)

        return directorRepository.save(director)
    }
}