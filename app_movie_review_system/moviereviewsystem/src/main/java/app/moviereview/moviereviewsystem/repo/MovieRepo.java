package app.moviereview.moviereviewsystem.repo;

import app.moviereview.moviereviewsystem.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    void deleteMovieById(Long id);

    Optional<Movie> findMovieById(Long id);
}
