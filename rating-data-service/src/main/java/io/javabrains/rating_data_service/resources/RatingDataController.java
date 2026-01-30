package io.javabrains.rating_data_service.resources;


import io.javabrains.rating_data_service.models.Rating;
import io.javabrains.rating_data_service.models.UserRatings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataController {

    @GetMapping("/{movieId}")
    public ResponseEntity<Rating> getRating(@PathVariable(name="movieId") String movieId){
        return ResponseEntity.ok(new Rating(movieId, 4));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserRatings> getUserRatings(@PathVariable("userId") String userId){
        System.out.println("simulating delay===================================================================");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Rating> ratings = List.of(
                new Rating("123", 10),
                new Rating("345", 9)
        );
        System.out.println("=======================================================================================================");
        return ResponseEntity.ok(new UserRatings(ratings));
    }

}
