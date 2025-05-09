package pl.pjatk.movieservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.movieservice.Film;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private List<Film> movies = new ArrayList<>(Arrays.asList(
            new Film("Incepcja", "Sci-Fi", 8.2, "Czasy, gdy technologia pozwala na wchodzenie w świat snów. Złodziej Cobb ma za zadanie wszczepić myśl do śpiącego umysłu."),
            new Film("Ojciec chrzestny", "Dramat", 8.6, "Opowieść o nowojorskiej rodzinie mafijnej. Starzejący się Don Corleone pragnie przekazać władzę swojemu synowi."),
            new Film("Shrek", "Animacja", 7.8, "By odzyskać swój dom, brzydki ogr z gadatliwym osłem wyruszają uwolnić piękną księżniczkę.")
    ));

    @GetMapping
    public ResponseEntity<List<Film>> getAllMovies() {
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getMovieById(@PathVariable UUID id) {
        Optional<Film> film = movies.stream().filter(f -> f.getId().equals(id)).findFirst();

        return film.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // {
    //  "name": "Matrix",
    //  "category": "Sci-Fi",
    //  "rating": 8.7,
    //  "description": "Neo odkrywa prawdę o rzeczywistości i dołącza do rebeliantów przeciwko maszynom."
    // }
    @PostMapping
    public ResponseEntity<Film> addMovie(@RequestBody Film movieFromRequest) {
        if (movieFromRequest.getName() == null || movieFromRequest.getName().isBlank() ||
                movieFromRequest.getCategory() == null || movieFromRequest.getCategory().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Film newFilm = new Film(
                movieFromRequest.getName(),
                movieFromRequest.getCategory(),
                movieFromRequest.getRating(),
                movieFromRequest.getDescription()
        );

        movies.add(newFilm);

        return ResponseEntity.ok(newFilm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateMovie(@PathVariable UUID id, @RequestBody Film movieDetails) {
        Optional<Film> filmOptional = movies.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();

        if (filmOptional.isPresent()) {
            Film filmToUpdate = filmOptional.get();

            filmToUpdate.setName(movieDetails.getName());
            filmToUpdate.setCategory(movieDetails.getCategory());
            filmToUpdate.setRating(movieDetails.getRating());
            filmToUpdate.setDescription(movieDetails.getDescription());

            return ResponseEntity.ok(filmToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        boolean removed = movies.removeIf(film -> film.getId().equals(id));

        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}