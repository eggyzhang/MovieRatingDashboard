package app.moviereview.moviereviewsystem.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String comment;
    private Long userId;
    private Long movieId;

    public Comment() {}

    public Comment(String comment, Long userId, Long movieId) {
        this.comment = comment;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", comment=" + comment +
                "}";
    }

}
