package io.javabrains.movie_catalog_service.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.javabrains.movie_catalog_service.models.CatalogItem;
import io.javabrains.movie_catalog_service.models.Movie;
import io.javabrains.movie_catalog_service.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate template;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getCatalogFallback",
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value="2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value="5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value="10000")
    })
    public ResponseEntity<List<CatalogItem>> getCatalog(@PathVariable(name = "userId") String userId){
        //get all the movies a user watched and the ratings given
        UserRatings ratings = template.getForObject("http://RATING-DATA-SERVICE/ratingsdata/users/"+userId, UserRatings.class);

        //for every movieId, get all the movie details consolidate into one response and return it to the clien
        List<CatalogItem> catalogItems = ratings.getRatings().stream().map(rating ->{
            Movie movie = template.getForObject("http://MOVIE-INFO-SERVICE/movies/"+rating.getMovieId(), Movie.class);

            //making above call using webclient instead of rest template
            /*Movie movie = webClientBuilder.build()
                    .get()                    .uri("http://localhost:8082/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/

            return new CatalogItem(movie.getName(), "description", rating.getRating());
        }).toList();

        return ResponseEntity.ok(catalogItems);


        /*return ResponseEntity.ok(Collections.singletonList(
                new CatalogItem("transformers", "test", 4)

        ));*/
    }

    public List<CatalogItem> getCatalogFallback(){
        System.out.println("Executing Fallback method");
        return new ArrayList<>();
    }
}
