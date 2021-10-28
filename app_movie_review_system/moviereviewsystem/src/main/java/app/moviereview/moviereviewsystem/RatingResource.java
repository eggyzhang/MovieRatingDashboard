package app.moviereview.moviereviewsystem;

import app.moviereview.moviereviewsystem.model.Rating;
import app.moviereview.moviereviewsystem.repo.RatingRepo;
import app.moviereview.moviereviewsystem.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/rating")
public class RatingResource {
    private static RatingRepo ratingServiceStatic;
    private final RatingService ratingService;

    public RatingResource(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rating>> getAllRatings() {
        List<Rating> ratings = ratingService.findAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable("id") Long id) {
        Rating rating = ratingService.findRatingById(id);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
        Rating newRating = ratingService.addRating(rating);
        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }

    @GetMapping("/find/user/{userId}/movie/{movieId}")
    public ResponseEntity<Rating> getRatingByUserIdAndMovieId(@PathVariable Long userId, @PathVariable Long movieId) {
        System.out.println("User ID: " + userId + "\nMovie ID: " + movieId);
        Rating rating = ratingService.getRatingByUserIdAndMovieId(userId, movieId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping("/find/movie/{movieId}")
    public ResponseEntity<Float> getAllRatingsByMovieId(@PathVariable Long movieId) {
        List<Rating> ratings = ratingService.findAllRatingsByMovieId(movieId);
        float sum = 0;
        int total = ratings.size();
        for (int i = 0; i < ratings.size(); i++) {
            sum += ratings.get(i).getRating();
        }
        float average = sum / total;

        return new ResponseEntity<>(average, HttpStatus.OK);
    }

//    public static Float getAverageRating(Long movieId) {
//        List<Rating> ratings = ratingServiceStatic.findAllRatingsByMovieId(movieId);
//        float sum = 0;
//        int total = ratings.size();
//        for (int i = 0; i < ratings.size(); i++) {
//            sum += ratings.get(i).getRating();
//        }
//        float average = sum / total;
//
//        return average;
//    }

    @PutMapping("/update")
    public ResponseEntity<Rating> updateRating(@RequestBody Rating rating) {
        System.out.println(rating);
        Rating specificRating = ratingService.findRatingByUserIdAndMovieId(rating);
        Rating updateRating;
        if (specificRating == null) {
            updateRating = ratingService.addRating(rating);
        } else {
            specificRating.setRating(rating.getRating());
            updateRating = ratingService.updateRating(specificRating);
        }
//        Rating updateRating = ratingService.updateRating(rating);
        return new ResponseEntity<>(updateRating, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable("id") Long id) {
        ratingService.deleteRating(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}