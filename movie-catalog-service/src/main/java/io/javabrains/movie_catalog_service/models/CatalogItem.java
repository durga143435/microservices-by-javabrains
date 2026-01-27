package io.javabrains.movie_catalog_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CatalogItem {
    private String name;
    private String desc;
    private int rating;
}
