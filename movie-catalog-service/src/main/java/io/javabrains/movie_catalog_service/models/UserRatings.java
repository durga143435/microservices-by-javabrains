package io.javabrains.movie_catalog_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserRatings {
    private List<Rating> ratings;
}
