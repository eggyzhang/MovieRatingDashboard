package app.moviereview.moviereviewsystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Like implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private boolean upvote;

    public Like() {}

    public Like(boolean upvote) {
        this.upvote = upvote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(boolean upvote) {
        this.upvote = upvote;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", upvote=" + upvote  +
                "}";
    }
}
