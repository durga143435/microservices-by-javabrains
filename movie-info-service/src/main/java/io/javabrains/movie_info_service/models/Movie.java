package io.javabrains.movie_info_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private String movieId;
    private String name;
}
