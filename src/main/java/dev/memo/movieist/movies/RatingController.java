package dev.memo.movieist.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {
    @Autowired
    private RatingService service;
    //need to implement that all the users that have written correspondant reviews be shown aswell.
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Rating>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Rating>>(service.findMovieByImdbId(imdbId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Rating> createRating(@RequestBody Map<String,String> payload){
        return new ResponseEntity<Rating>(service.createRating(payload.get("imbdId"), payload.get("userId"), payload.get("ratingValue")), HttpStatus.OK);
    }
}
