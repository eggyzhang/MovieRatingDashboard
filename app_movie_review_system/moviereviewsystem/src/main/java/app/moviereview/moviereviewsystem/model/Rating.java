package app.moviereview.moviereviewsystem.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private float rating;
    private Long userId;
    private Long movieId;

    public Rating() {}

    public Rating(float rating, Long userId, Long movieId) {
        this.rating = rating;
        this.userId = userId;
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() { return movieId; }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", rating=" + rating +
                "}";
    }

}
