package com.osvaldopereyra.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osvaldopereyra.apirest.entities.Movie;

public interface IMovieRepository extends JpaRepository<Movie, Long> {

}
