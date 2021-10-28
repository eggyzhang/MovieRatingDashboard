package app.moviereview.moviereviewsystem.repo;

import app.moviereview.moviereviewsystem.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentById(Long id);

    Optional<Comment> findCommentByUserIdAndMovieId(Long userId, Long movieId);

    List<Comment> findAllCommentsByMovieId(Long movieId);
}
