package dev.farhan.movieist.movies;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends MongoRepository<Rating, ObjectId> {
    Optional<Rating> findMovieByImdbId(String imdbId);
}

