package app.moviereview.moviereviewsystem.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String title;
    private String plot;
    private String director;
    private String writers;
    private String genre;
    private String releaseDate;
    private String language;
    private String imageUrl;
    private float avgRating;
    @Column(nullable = false, updatable = false)
    private String movieCode;

    public Movie() {}

    public Movie(String title, String plot, String director, String writers, String genre, String releaseDate, String language, String imageUrl) {
        this.title = title;
        this.plot = plot;
        this.director = director;
        this.writers = writers;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.language = language;
        this.imageUrl = imageUrl;
        this.avgRating = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMovieCode() {
        return movieCode;
    }

    public void setMovieCode(String movieCode) {
        this.movieCode = movieCode;
    }

    public float getAvgRating() { return avgRating; }

    public void setAvgRating(float avgRating) { this.avgRating = avgRating; }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title=" + title +
                ", plot=" + plot +
                ", director=" + director +
                ", writers=" + writers +
                ", genre=" + genre +
                ", releaseDate=" + releaseDate +
                ", language=" + language +
                ", imageUrl=" + imageUrl +
                ", movieCode=" + movieCode +
                "}";
    }
}



