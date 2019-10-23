package com.example.MovieReviewBoard.movie

import com.example.MovieReviewBoard.director.Director
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Movie(
        var title: String,
        @ManyToOne var director: Director,
        var rating: Int,
        var releaseDate: String,
        @Id @GeneratedValue var id: Int? = null)