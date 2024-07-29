/*
*
* HTTP: Protocol to send the information.
* 
* GET -> Get information.
* POST -> Create new resource.
* PUT -> Update an existing resource with new version.
* DELETE -> Delete an resource.
*/

package com.osvaldopereyra.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osvaldopereyra.apirest.entities.Movie;
import com.osvaldopereyra.apirest.repositories.IMovieRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMovieRepository movieRepository;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The film don't found. id=" + id));
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PutMapping("/{id}")
    public Movie updateProductById(@PathVariable Long id, @RequestBody Movie changes) {
        // Getting the movie and throw exteption if do not found.
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The film don't found. id=" + id));
        // Update the movie.
        movie.setTitle(changes.getTitle());
        movie.setSynopsis(changes.getSynopsis());
        movie.setCategories(changes.getCategories());
        movie.setDuration(changes.getDuration());
        movie.setCountry(changes.getCountry());
        movie.setDirector(changes.getDirector());
        // Save the movie with changes.
        return movieRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    public Movie deleteMovieById(@PathVariable Long id) {
        // Getting the movie and throw exteption if do not found.
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The film don't found. id=" + id));
        // Deleting the movie.
        movieRepository.delete(movie);
        // Return the deleted movie.
        return movie;
    }

}
