package app.moviereview.moviereviewsystem.repo;

import app.moviereview.moviereviewsystem.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepo extends JpaRepository<Like, Long> {
    void deleteLikeById(Long id);

    Optional<Like> findLikeById(Long id);
}
