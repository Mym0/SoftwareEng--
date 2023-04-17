package dev.farhan.movieist.movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    
    @Autowired
    private RatingRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Rating createRating(String imbdId, String userId, String ratingValue) {
        Rating rating = repository.insert(new Rating(imbdId, userId, ratingValue));
        //DELETE
        //Criteria.where("userId").is(userId), cant get the userId to be part of the Where clause
        mongoTemplate.update(Movie.class)
        .matching(Criteria.where("imdbId").is(imbdId))
        .apply(new Update().push("ratings").value(rating))
        .first();
        
        return rating;
    }

    public Optional<Rating> findMovieByImdbId(String imdbId) {
        return repository.findMovieByImdbId(imdbId);
    }
    
}