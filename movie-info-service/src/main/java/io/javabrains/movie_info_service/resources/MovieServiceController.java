package io.javabrains.movie_info_service.resources;


import io.javabrains.movie_info_service.models.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieServiceController {

    @RequestMapping("/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable("movieId") String movieId){
        return ResponseEntity.ok(new Movie(movieId, "Test name"+movieId));
    }
}
