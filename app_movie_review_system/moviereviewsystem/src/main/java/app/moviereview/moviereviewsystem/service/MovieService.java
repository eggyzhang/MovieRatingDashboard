package app.moviereview.moviereviewsystem.service;

import app.moviereview.moviereviewsystem.exception.MovieNotFoundException;
import app.moviereview.moviereviewsystem.model.Movie;
import app.moviereview.moviereviewsystem.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {
    private final MovieRepo movieRepo;

    @Autowired
    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public Movie addMovie(Movie movie) {
        movie.setMovieCode(UUID.randomUUID().toString());
        return movieRepo.save(movie);
    }

    public List<Movie> findAllMovies() {
        return movieRepo.findAll();
    }

    public Movie updateMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public Movie findMovieById(Long id) {
        return movieRepo.findMovieById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie by id " + id + " was not found"));
    }

    public void deleteMovie(Long id) {
        movieRepo.deleteMovieById(id);
    }
}
