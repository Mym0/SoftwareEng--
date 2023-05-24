package dev.memo.movieist.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private ObjectId id;
    private String imdbId;
    private String userId;
    private String ratingValue;

    public Rating(String imdbId, String userId, String ratingValue) {
        this.imdbId = imdbId;
        this.userId = userId;
        this.ratingValue = ratingValue;
    }
}
