package app.moviereview.moviereviewsystem;

import app.moviereview.moviereviewsystem.model.Like;
import app.moviereview.moviereviewsystem.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/like")
public class LikeResource {
    private final LikeService likeService;

    public LikeResource(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Like>> getAllLikes() {
        List<Like> likes = likeService.findAllLikes();
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Like> getLikeById(@PathVariable("id") Long id) {
        Like like = likeService.findLikeById(id);
        return new ResponseEntity<>(like, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Like> addLike(@RequestBody Like like) {
        Like newLike = likeService.addLike(like);
        return new ResponseEntity<>(newLike, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Like> updateLike(@RequestBody Like like) {
        Like updateLike = likeService.updateLike(like);
        return new ResponseEntity<>(updateLike, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable("id") Long id) {
        likeService.deleteLike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
