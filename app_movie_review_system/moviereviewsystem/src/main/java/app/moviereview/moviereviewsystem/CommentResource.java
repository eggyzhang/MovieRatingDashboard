package app.moviereview.moviereviewsystem;

import app.moviereview.moviereviewsystem.model.Comment;
import app.moviereview.moviereviewsystem.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/comment")
public class CommentResource {
    private final CommentService commentService;

    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all/{movieId}")
    public ResponseEntity<List<Comment>> getAllCommentsByMovieId(@PathVariable("movieId") Long movieId) {
        List<Comment> comments = commentService.findAllCommentsByMovieId(movieId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") Long id) {
        Comment comment = commentService.findCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @GetMapping("/find/user/{userId}/movie/{movieId}")
    public ResponseEntity<Comment> getCommentByUserIdAndMovieId(@PathVariable Long userId, @PathVariable Long movieId) {
        System.out.println("User ID: " + userId + "\nMovie ID: " + movieId);
        Comment comment = commentService.getCommentByUserIdAndMovieId(userId, movieId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment) {
        System.out.println(comment);
        Comment specificComment = commentService.findCommentByUserIdAndMovieId(comment);
        Comment updateComment;
        if (specificComment == null) {
            updateComment = commentService.addComment(comment);
        } else {
            specificComment.setComment(comment.getComment());
            updateComment = commentService.updateComment(specificComment);
        }
//        Comment updateComment = commentService.updateComment(comment);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}