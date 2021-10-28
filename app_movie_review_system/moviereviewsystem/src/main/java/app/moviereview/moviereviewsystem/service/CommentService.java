package app.moviereview.moviereviewsystem.service;

import app.moviereview.moviereviewsystem.exception.CommentNotFoundException;
import app.moviereview.moviereviewsystem.model.Comment;
import app.moviereview.moviereviewsystem.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment addComment(Comment comment) {
        return commentRepo.save(comment);
    }

    public List<Comment> findAllCommentsByMovieId(Long movieId) {
        return commentRepo.findAllCommentsByMovieId(movieId);
    }

    public Comment updateComment(Comment comment) {
        return commentRepo.save(comment);
    }

    public Comment findCommentByUserIdAndMovieId(Comment comment) {
        return commentRepo.findCommentByUserIdAndMovieId(comment.getUserId(), comment.getMovieId()).orElse(null); }

    public Comment getCommentByUserIdAndMovieId(Long userId, Long movieId) {
        return commentRepo.findCommentByUserIdAndMovieId(userId, movieId).orElse(null); }

    public Comment findCommentById(Long id) {
        return commentRepo.findCommentById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment by id " + id + " was not found"));
    }

    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }
}