package app.moviereview.moviereviewsystem.service;

import app.moviereview.moviereviewsystem.exception.RatingNotFoundException;
import app.moviereview.moviereviewsystem.model.Rating;
import app.moviereview.moviereviewsystem.repo.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RatingService {
    private final RatingRepo ratingRepo;

    @Autowired
    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public Rating addRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    public List<Rating> findAllRatings() {
        return ratingRepo.findAll();
    }

    public List<Rating> findAllRatingsByMovieId(Long movieId) {
        return ratingRepo.findAllRatingsByMovieId(movieId);
    }

    public Rating updateRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    public Rating findRatingByUserIdAndMovieId(Rating rating) {
        return ratingRepo.findRatingByUserIdAndMovieId(rating.getUserId(), rating.getMovieId()).orElse(null); }

    public Rating getRatingByUserIdAndMovieId(Long userId, Long movieId) {
        return ratingRepo.findRatingByUserIdAndMovieId(userId, movieId).orElse(null); }

    public Rating findRatingById(Long id) {
        return ratingRepo.findRatingById(id)
                .orElseThrow(() -> new RatingNotFoundException("Rating by id " + id + " was not found"));
    }

    @Transactional
    public void deleteRating(Long id) {
        ratingRepo.deleteRatingById(id);
    }
}