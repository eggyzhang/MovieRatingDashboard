package app.moviereview.moviereviewsystem.service;

import app.moviereview.moviereviewsystem.exception.LikeNotFoundException;
import app.moviereview.moviereviewsystem.model.Like;
import app.moviereview.moviereviewsystem.repo.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    private final LikeRepo likeRepo;

    @Autowired
    public LikeService(LikeRepo likeRepo) {
        this.likeRepo = likeRepo;
    }

    public Like addLike(Like like) {
        return likeRepo.save(like);
    }

    public List<Like> findAllLikes() {
        return likeRepo.findAll();
    }

    public Like updateLike(Like like) {
        return likeRepo.save(like);
    }

    public Like findLikeById(Long id) {
        return likeRepo.findLikeById(id)
                .orElseThrow(() -> new LikeNotFoundException("Like by id " + id + " was not found"));
    }

    public void deleteLike(Long id) {
        likeRepo.deleteLikeById(id);
    }
}
