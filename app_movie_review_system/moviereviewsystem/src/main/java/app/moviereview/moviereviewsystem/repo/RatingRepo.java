package app.moviereview.moviereviewsystem.repo;

import app.moviereview.moviereviewsystem.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import java.util.Optional;

public interface RatingRepo extends JpaRepository<Rating, Long> {
    void deleteRatingById(Long id);

    Optional<Rating> findRatingById(Long id);

    Optional<Rating> findRatingByUserIdAndMovieId(Long userId, Long movieId);

    List<Rating> findAllRatingsByMovieId(Long movieId);
}
