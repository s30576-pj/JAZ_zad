package pl.pjatk.movieservice;

import java.util.UUID;

public class Film {

    private UUID id;
    private String name;
    private String category;
    private double rating;
    private String description;

    public Film(String name, String category, double rating, String description) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}