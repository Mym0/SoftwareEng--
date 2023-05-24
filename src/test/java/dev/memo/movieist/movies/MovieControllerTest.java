package dev.memo.movieist.movies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MovieControllerTest {
    @Test
    void testGetMovies() {
        // MovieService service = Mockito.mock(MovieService.class);
        MovieService service = mock(MovieService.class);

        // Create sample movie list
        List<Movie> movies = Arrays.asList(
            new Movie("1", "Movie 1", null, null, null, null, null, null),
            new Movie("1", "Movie 1", null, null, null, null, null, null)
        );
        
        // Stub the findAllMovies() method to return the sample movie list
        when(service.findAllMovies()).thenReturn(movies);
        
        // Create an instance of the MovieController
        MovieController controller = new MovieController(service);
        
        // Call the getMovies() method
        ResponseEntity<List<Movie>> response = controller.getMovies();
        
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movies, response.getBody());
    }
}
